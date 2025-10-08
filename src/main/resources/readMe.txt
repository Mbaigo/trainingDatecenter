com.mbaigo.trainingTools.training_tools
│
├── TrainingToolsApplication.java
│
├── config/
│   ├── JpaConfig.java
│   ├── SecurityConfig.java
│   ├── JwtConfig.java
│
├── domain/
│   ├── userRole/
│   │   ├── Utilisateur.java
│   │   ├── Role.java
│   │   └── RefreshToken.java
│   └── training/
│       ├── Trainer.java
│       ├── Course.java
│       └── Session.java
│
├── repository/
│   ├── userRole/
│   │   ├── UserRepository.java
│   │   ├── RoleRepository.java
│   │   └── RefreshTokenRepository.java
│   └── training/
│       ├── TrainerRepository.java
│       ├── CourseRepository.java
│       └── SessionRepository.java
│
├── service/
│   ├── userRole/
│   │   ├── RefreshTokenService.java
│   │   └── UserService.java
│   └── training/
│       ├── TrainerService.java
│       └── CourseService.java
│
├── controller/
│   ├── userRole/
│   │   ├── AuthController.java
│   │   └── RoleController.java
│   └── training/
│       ├── TrainerController.java
│       └── CourseController.java
│
└── exception/
    ├── TrainingApiException.java
    └── GlobalExceptionHandler.java
