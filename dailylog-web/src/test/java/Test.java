import com.dailylog.GenericException;
import com.dailylog.manager.UserManager;

public class Test {

	public static void main(String[] args) throws GenericException {

		UserManager mgr = AppContextHelper.getInstance().getBean(UserManager.class);

		mgr.login("ee", "ÍÞ¹þ¹þ");
	}
}
