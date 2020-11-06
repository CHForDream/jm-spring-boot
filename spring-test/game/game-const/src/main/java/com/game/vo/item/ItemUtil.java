package com.game.vo.item;

import java.util.List;

import com.game.utils.StringUtils;
import com.google.common.collect.Lists;

public class ItemUtil {
	/**
	 * 解析道具字符串为List<{@link ItemVo}>
	 * 
	 * @param itemArrayStr
	 * @return
	 */
	public static List<ItemVo> fromItemArrayString(String itemArrayStr) {
		List<ItemVo> resultList = Lists.newArrayList();
		if (!StringUtils.isEmpty(itemArrayStr)) {
			String[] itemStrArray = itemArrayStr.split(";");
			for (String itemStr : itemStrArray) {
				ItemVo item = fromItemString(itemStr);
				if (item != null) {
					resultList.add(item);
				}
			}
		}
		return resultList;
	}

	/**
	 * 解析单个道具字符串为{@link ItemVo}
	 * 
	 * @param itemStr
	 * @return
	 */
	public static ItemVo fromItemString(String itemStr) {
		String[] split = itemStr.split(",");
		if (split.length != 3) {
			System.out.println("Error itemStr: " + itemStr);
			return null;
		}

		try {
			return new ItemVo(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<ItemVo> merge(List<ItemVo> infoList) {
		List<ItemVo> result = Lists.newArrayList();
		for (ItemVo bagInfo : infoList) {
			boolean isMerged = false;
			for (ItemVo targetBagInfo : result) {
				if (bagInfo.getType() == targetBagInfo.getType() && bagInfo.getId() == targetBagInfo.getId()) {
					targetBagInfo.setNum(targetBagInfo.getNum() + bagInfo.getNum());
					isMerged = true;
				}
			}
			if (!isMerged) {
				result.add(bagInfo.clone());
			}
		}
		return result;
	}

	@SafeVarargs
	public static List<ItemVo> merge(List<ItemVo>... infoList) {
		List<ItemVo> result = Lists.newArrayList();
		for (List<ItemVo> bagInfoList : infoList) {
			for (ItemVo bagInfo : bagInfoList) {
				boolean isMerged = false;
				for (ItemVo targetBagInfo : result) {
					if (bagInfo.getType() == targetBagInfo.getType() && bagInfo.getId() == targetBagInfo.getId()) {
						targetBagInfo.setNum(targetBagInfo.getNum() + bagInfo.getNum());
						isMerged = true;
					}
				}

				if (!isMerged) {
					result.add(bagInfo.clone());
				}
			}
		}
		return result;
	}

	public static String toInfoString(List<ItemVo> infoList) {
		if (infoList == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (ItemVo bagInfo : infoList) {
//			4,1,1;4,1,1;4,1,1
			if (!isFirst) {
				sb.append(";");
			} else {
				isFirst = false;
			}
			sb.append(bagInfo.getType()).append(",").append(bagInfo.getId()).append(",").append(bagInfo.getNum());
		}
		return sb.toString();
	}
}
