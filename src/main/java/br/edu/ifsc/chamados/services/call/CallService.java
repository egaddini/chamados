package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.models.call.Call;
import br.edu.ifsc.chamados.models.call.CallType;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.repositories.CallRepository;
import br.edu.ifsc.chamados.repositories.StatusRepository;
import br.edu.ifsc.chamados.requests.CallRequest;
import br.edu.ifsc.chamados.response.call.CallResponse;
import br.edu.ifsc.chamados.response.user.UserTinyResponse;
import br.edu.ifsc.chamados.services.user.UserService;
import br.edu.ifsc.chamados.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallService {

    @Autowired
    private CallRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private CallTypeService callTypeService;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private HistoricService historicService;

    public List<CallResponse> findAll(String email) {
        List<Call> calls = new ArrayList<>();
        calls = (email == null || email.isEmpty()) ? repository.findAll() : repository.findAllBySolicitante_email(email);
        return calls.stream().map(i ->
            new CallResponse(i.getId(), i.getDataCriacao(), i.getDataUltAtualizacao(), i.getStatus().getName(),
            new UserTinyResponse(i.getSolicitante().getId(), i.getSolicitante().getEmail()),
            null, i.getCallType(), i.getDescricao(), i.getHistorico())).collect(Collectors.toList());
    }
    public Call findById(Integer id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception(id.toString()));
    }

    public Call register(Call call) {
        return repository.save(call);
    }

    public SucessDTO register(CallRequest request) throws RecordNotFound2Exception {
        User solicitante = userService.findUserById(request.getSolicitante());
        CallType callType = callTypeService.findUserById(request.getCallType());
        LocalDateTime hora = LocalDateTime.now();
        String formatter = DateUtils.dateTime2StringFormatted(hora);

        Call call = new Call();
        call.setDataCriacao(hora);
        call.setDataUltAtualizacao(hora);
        call.setStatus(statusRepository.findByWeight(1).orElseThrow(() ->  new RecordNotFound2Exception("1")));
        call.setSolicitante(solicitante);
        call.setResponsavel(null);
        call.setCallType(callType);
//        call.setCallParticipants(Arrays.asList(new CallParticipants(null, call, solicitante)));
        call.setDescricao(request.getDescricao());
        repository.save(call);
        call.setHistorico(Arrays.asList(historicService.saveHistoric(solicitante.getEmail(), "Chamado Iniciado", call)));
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public SucessDTO cancela(Integer id, String motivo) throws RecordNotFound2Exception {
        String dataHoraFormatado = DateUtils.getDataHoraFormatado();
        Call call = findUserById(id);
        repository.save(call);
        return new SucessDTO("Solicitação realizada com sucesso.");
    }

    public Call findUserById(Integer id) throws RecordNotFound2Exception {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound2Exception(id.toString()));
    }

}
