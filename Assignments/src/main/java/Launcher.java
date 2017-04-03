import controller.LoginViewController;
import view.WelcomeLoginView;

public class Launcher {

    public static void main(String[] args) {


        ComponentFactory componentFactory = ComponentFactory.getInstance();
        new LoginViewController(new WelcomeLoginView(), componentFactory.getUserService(), componentFactory.getClientService(), componentFactory.getAccountService());
    }

}
