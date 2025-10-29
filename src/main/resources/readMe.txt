com.mbaigo.trainingtools.training_tools
├── auth
│   ├── controller
│   │   └── AuthController.java
│   ├── dto
│   │   ├── LoginRequest.java
│   │   ├── RegisterStep1Request.java
│   │   ├── UpdateUserDetailsRequest.java
│   │   ├── ProfilRequest.java
│   │   ├── JwtResponse.java
│   │   └── TokenRefreshRequest.java
│   ├── service
│   │   ├── AuthService.java
│   │   └── impl
│   │       └── AuthServiceImpl.java
│   └── token
│       ├── RefreshToken.java
│       ├── repository
│       │   └── RefreshTokenRepository.java
│       └── service
│           ├── RefreshTokenService.java
│           └── impl
│               └── RefreshTokenServiceImpl.java
├── security
│   ├── JwtUtil.java
│   └── SecurityConfig.java
├── user
│   ├── entity
│   │   ├── Utilisateur.java (abstraite)
│   │   ├── Trainer.java
│   │   ├── Learner.java
│   │   ├── Admin.java
│   │   ├── Profil.java
│   │   └── ConnexionHistory.java
│   ├── repository
│   │   ├── UtilisateurRepository.java
│   │   ├── TrainerRepository.java
│   │   ├── LearnerRepository.java
│   │   ├── AdminRepository.java
│   │   ├── ProfilRepository.java
│   │   └── ConnexionHistoryRepository.java
│   └── service
│       ├── UtilisateurService.java
│       ├── ProfilService.java
│       ├── ConnexionHistoryService.java
│       └── impl
│           ├── UtilisateurServiceImpl.java
│           ├── ProfilServiceImpl.java
│           └── ConnexionHistoryServiceImpl.java
└── exception
    └── GlobalExceptionHandler.java

    PROFIL LEARNER
    🧠 Bonus : Vision UX (façon Coursera)

    🎓 Après inscription → “Aide-nous à créer ton plan d’étude”

    🔍 Choix du domaine → “Dans quel domaine veux-tu te spécialiser ?”

    🕒 Choix du rythme → “Combien d’heures par semaine peux-tu y consacrer ?”

    🎯 Objectif → “Quel est ton objectif final ?”

    🧩 Génération automatique du LearningPlan initial

    ✅ Sauvegarde + redirection vers un tableau de bord de progressio
