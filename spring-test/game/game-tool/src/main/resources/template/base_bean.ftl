package ${ConfigPackage};

import com.game.common.data.config.build.IInitBean;
import com.game.common.data.define.BaseBean;

/**
 * 文件名：${Name?cap_first}BaseBean.java
 * <p>
 * 功能：${FileName} -> ${Name}BaseBean
 * <p>
 * 版本：1.0.0
 * <p>
 * 作者：Create by AppTools
 * <p>
 */
public class ${Name?cap_first}BaseBean extends BaseBean implements IInitBean {
<#if FieldBeans?size!=0>
	<#list FieldBeans as field>
	<#if field.enName != IdColunm>
	/** ${field.znName} */
	<#if field.fieldType == "int">
	private ${field.fieldType} ${field.enName} = 0;
	</#if>
	<#if field.fieldType == "long">
	private ${field.fieldType} ${field.enName} = 0;
	</#if>
	<#if field.fieldType == "String">
	private ${field.fieldType} ${field.enName} = null;
	</#if>
	<#if field.fieldType == "float">
	private ${field.fieldType} ${field.enName} = 0.0f;
	</#if>
	</#if>
	</#list>
</#if>

<#if FieldBeans?size!=0>
<#list FieldBeans as field>
<#if field.enName != IdColunm >
	/** ${field.znName} */
	public ${field.fieldType} get${field.enName?cap_first}() {
		return ${field.enName};
	}

	/** ${field.znName} */
	public void set${field.enName?cap_first}(${field.fieldType} ${field.enName}) {
		this.${field.enName} = ${field.enName};
	}

</#if>
</#list>
</#if>
	@Override
	public void initBean(String[] keyArray, String[] data) {
		// id
		if (data[0] == null || "".equals(data[0].trim())) {
			this.id = 0;
		} else {
			this.id = Integer.parseInt(data[0]);
		}

		for (int i = 1; i < keyArray.length; i++) {
			String key = keyArray[i];
			String value = data[i];

			// id
			if (key.equals("id")) {
				if (value == null || "".equals(value.trim())) {
					this.id = 0;
				} else {
					this.id = Integer.parseInt(value);
				}
			}
<#if FieldBeans?size!=0>
	<#list FieldBeans as field>
		<#if field.enName != IdColunm>

			// ${field.znName}
			if (key.equals("${field.enName}")) {
			<#if field.fieldType == "int">
				if (value == null || "".equals(value.trim())) {
					this.${field.enName} = 0;
				} else {
					this.${field.enName} = Integer.parseInt(value);
				}
			</#if>
			<#if field.fieldType == "long">
				if (value == null || "".equals(value.trim())) {
					this.${field.enName} = 0;
				} else {
					this.${field.enName} = Long.parseLong(value);
				}
			</#if>
			<#if field.fieldType == "String">
				this.${field.enName} = value;
			</#if>
			<#if field.fieldType == "float">
				if (value == null || "".equals(value.trim())) {
					this.${field.enName} = 0;
				} else {
					this.${field.enName} = Float.parseFloat(value);
				}
			</#if>
			}
		</#if>
	</#list>
</#if>
		}
	}
}