//package br.edu.ifsc.chamados.models.call;
//
//import br.edu.ifsc.chamados.models.user.User;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//
//@Data
//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class CallParticipants implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "call_id")
//    private Call call;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//}
