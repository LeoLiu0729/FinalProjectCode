package com.csc340.jpacruddemo.Audit;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;

    @Entity
    @Table(name = "audit_log")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Audit {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long activityId;

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "action_type")
        private String actionType;

        @Column(name = "action_description")
        private String actionDescription;

        @Column(name = "timestamp")
        private LocalDateTime timestamp;

        @Column(name = "affected_entity_id")
        private Long affectedEntityId;

        public Audit(Long userId, String actionType, String actionDescription, Long affectedEntityId) {
            this.userId = userId;
            this.actionType = actionType;
            this.actionDescription = actionDescription;
            this.timestamp = LocalDateTime.now(); 
            this.affectedEntityId = affectedEntityId;
        }

    }