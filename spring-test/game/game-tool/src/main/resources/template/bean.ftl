package ${ConfigPackage};

import org.springframework.stereotype.Component;

import com.game.common.data.config.build.DataDefine;
import ${ConfigPackage}.base.${Name?cap_first}BaseBean;

/**
 * 文件名：${Name?cap_first}Bean.java
 * <p>
 * 功能：${FileName} -> ${Name}Bean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
@Component
@DataDefine(configFileName = "${FileName}", name = "${Name}", sheetFileName = "${SheetName}")
public class ${Name?cap_first}Bean extends ${Name?cap_first}BaseBean {
}