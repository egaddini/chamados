package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.enums.CallStatus;
import br.edu.ifsc.chamados.models.call.Call;
import br.edu.ifsc.chamados.models.call.CallParticipants;
import br.edu.ifsc.chamados.models.call.CallType;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.repositories.CallRepository;
import br.edu.ifsc.chamados.repositories.CallTypeRepository;
import br.edu.ifsc.chamados.requests.CallRequest;
import br.edu.ifsc.chamados.response.call.CallResponse;
import br.edu.ifsc.chamados.response.user.UserResponse;
import br.edu.ifsc.chamados.response.user.UserTinyResponse;
import br.edu.ifsc.chamados.services.user.UserService;
import br.edu.ifsc.chamados.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallService {

    @Autowired
    private CallRepository callRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CallTypeService callTypeService;

    public List<CallResponse> findAll() {
        return callRepo.findAll().stream().map(i ->
            new CallResponse(i.getId(), i.getDataCriacao(), i.getDataUltAtualizacao(), i.getStatus(),
            new UserTinyResponse(i.getSolicitante().getId(),
            i.getSolicitante().getEmail()),
            new UserTinyResponse(i.getResponsavel().getId(), i.getResponsavel().getEmail()),
            i.getCallType(), i.getDescricao(), i.getHistorico())).collect(Collectors.toList());
    }

    public SucessDTO register(CallRequest request) throws RecordNotFound2Exception {

        User solicitante = userService.findUserById(request.getSolicitante());
        CallType callType = callTypeService.findUserById(request.getCallType());
        LocalDateTime hora = LocalDateTime.now();
        String formatter = DateUtils.dateTime2StringFormatted(hora);
        String registroHistorico = String.format("%s - Usuário %s, iniciou o chamado: %s.", formatter, solicitante.getEmail(), callType.getTitulo());

        Call call = new Call();
        call.setDataCriacao(hora);
        call.setDataUltAtualizacao(hora);
        call.setStatus(CallStatus.ABERTO);
        call.setSolicitante(solicitante);
        call.setResponsavel(null);
        call.setCallType(callType);
        call.setCallParticipants(Arrays.asList(new CallParticipants(null, call, solicitante)));
        call.setDescricao(request.getDescricao());
        call.setHistorico(registroHistorico);
        callRepo.save(call);

        return new SucessDTO("Solicitação realizada com sucesso.");
    }
}
