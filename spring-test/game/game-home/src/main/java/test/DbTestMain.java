package test;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.game.db.DaoService;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;

@SpringBootApplication
@ComponentScan(basePackages = { "com" })
@EnableAsync
@EnableCaching
@SuppressWarnings("unused")
public class DbTestMain {
	public static void main(String[] args) throws IOException {
		Globals.applicationContext = SpringApplication.run(DbTestMain.class, args);

		DaoService gameDaoService = (DaoService) Globals.applicationContext.getBean("DaoService");

		// 更新玩家名字(兼容utf8mb4)
//		updateUserName(gameDaoService);
	}

	private static void updateUserName(DaoService gameDaoService) {
		RoleEntity role = gameDaoService.getRoleDao().get(RoleEntity.class, 11589968966001L);
		System.out.println(role.getName());

//		role.setName("👰/aaa👰");
//		gameDaoService.getBaseDao().update(role);
	}

}
