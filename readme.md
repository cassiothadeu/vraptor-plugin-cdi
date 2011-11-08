vraptor-plugin-cdi
------------------

This plugin allows you to inject CDI Beans in your VRaptor project.

You only need copy the vraptor-plugin-cdi.jar to your classpath. You don't need to configure anything. The CDI plugin will be loaded at VRaptor startup.

At this time you need to create a factory for each CDI beans that you want to lookup like this:

    @Component
    @RequestScoped
    public class AuthenticationServiceFactory
        implements ComponentFactory<AuthenticationServiceLocal> {
    
        private final ServiceLocator serviceLocator;
    
        public AuthenticationServiceFactory(ServiceLocator serviceLocator) {
            this.serviceLocator = serviceLocator;
        }
    
        @Override
        public AuthenticationServiceLocal getInstance() {
            return serviceLocator.get(AuthenticationServiceLocal.class);
        }
    }

After you can receive your CDI beans into your VRaptor classes:

    @Component
    @SessionScoped
    public class LoginController {
    
        private final AuthenticationServiceLocal authenticationService;
    
        public LoginController(AuthenticationServiceLocal authenticationService) {
            this.authenticationService = authenticationService;
        }
    }
