package br.edu.ifsc.chamados.services.call;

import br.edu.ifsc.chamados.configs.exceptions.RecordNotFound2Exception;
import br.edu.ifsc.chamados.dto.SucessDTO;
import br.edu.ifsc.chamados.enums.CallStatus;
import br.edu.ifsc.chamados.models.call.Call;
import br.edu.ifsc.chamados.models.call.CallType;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.repositories.CallRepository;
import br.edu.ifsc.chamados.repositories.CallTypeRepository;
import br.edu.ifsc.chamados.requests.CallRequest;
import br.edu.ifsc.chamados.services.user.UserService;
import br.edu.ifsc.chamados.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
public class CallService {

    @Autowired
    private CallRepository callRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CallTypeService callTypeService;

    public SucessDTO register(CallRequest request) throws RecordNotFound2Exception {

        User solicitante = userService.findUserById(request.getSolicitante());
        CallType callType = callTypeService.findUserById(request.getCallType());
        LocalDateTime hora = LocalDateTime.now();
        String formatter = DateUtils.dateTime2StringFormatted(hora);
        String registroHistorico = String.format("%s - Usuário %s, iniciou o chamado: %s.", formatter, solicitante.getEmail(), callType.getTitulo());

        List<User> participantes = new ArrayList<>(1);
        participantes.add(solicitante);

        Call call = Call.builder()
            .dataCriacao(hora)
            .dataUltAtualizacao(hora)
            .status(CallStatus.ABERTO)
            .solicitante(solicitante)
            .responsavel(null)
            .callType(callType)
            //.participantes(null)
            .descricao(request.getDescricao())
            .historico(registroHistorico)
            .build();

        callRepo.save(call);

        return new SucessDTO("Solicitação realizada com sucesso.");
    }
}
