# MuzixApp
->Created endpoints
1.Savetrack
2.GetAlltracks
3.Updatecomments
4.deletetrack
5.gettrackbyname
->Used h2-console and mysql
->Added an endpoint to search trackByName. Understand @Query and parameter passing to@Query
->Generated API documentation using Swagger 2
->Created custom exceptions TrackNotFoundException, TrackAlreadyExistsException in a exceptions package.
  Performed appropriate exception handling and propagationBack.
->Created seed data to pre-fill the database with trackinformation whenever the application starts.
  Used both approaches
      1: ApplicationListener<ContextRefreshedEvent>Approach
      2: CommandLineRunner
->Global exception using Controller advice and ExceptionHandler
->Removed all hard coded data from the application code to application.propertiesa)
      by using
      1.@Value.
      2. @PropertySource
      3.Used Environment interface
    
->Added Lombok plugin
->Provided a second implementation of MuzixService.
   Named it MuzixServiceConsoleImpl and
   used @Primary and @Qualifier annotations for the required implementation.
