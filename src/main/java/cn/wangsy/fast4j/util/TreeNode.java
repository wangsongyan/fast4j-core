package cn.wangsy.fast4j.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

/** 
 * 说明：easyui树节点
 * <p>每个节点可以包括下列属性：</p>
 * <ul>
 * <li>id：节点的 id，它对于加载远程数据很重要。</li>
 * <li>text：要显示的节点文本。</li>
 * <li>state：节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。</li>
 * <li>checked：指示节点是否被选中。</li>
 * <li>attributes：给一个节点添加的自定义属性。</li>
 * <li>children：定义了一些子节点的节点数组。</li>
 * </ul>
 * @author wangsy
 * @date 创建时间：2016年9月8日 上午9:57:25
 */
public class TreeNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 771479771622473618L;
	public static final String STATE_OPEN = "open";
	public static final String STATE_CLOSED = "closed";
	
	private String id;
	private String text;
	private String state;
	private String checked;
	private Map<String, Object> attributes = new HashMap<String, Object>();
	private List<TreeNode> children = new ArrayList<TreeNode>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		Assert.isTrue(STATE_OPEN.equals(state) || STATE_CLOSED.equals(state), "error state! state must be \""+STATE_OPEN+"\" or \""+STATE_CLOSED+"\"");
		this.state = state;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	// 添加单个子节点
	public void addChild(TreeNode treeNode){
		Assert.notNull(children, "null children!");
		children.add(treeNode);
	}
	
	// 添加多个子节点
	public void addChildren(List<TreeNode> treeNodes){
		Assert.notNull(children, "null children!");
		children.addAll(treeNodes);
	}
	
}
