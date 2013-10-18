import net.jetidea.forge.dailylog.GenericException;
import net.jetidea.forge.dailylog.manager.UserManager;

public class Test {

	public static void main(String[] args) throws GenericException {

		UserManager mgr = AppContextHelper.getInstance().getBean(UserManager.class);

		mgr.login("ed", "ÍÞ¹þ¹þ");
	}
}
