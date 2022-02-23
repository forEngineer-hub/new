package ltd.newbee.mall.entity;
public class KaguInfo {
  public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
private Integer id;      // 家具ID
  private String name;     // 家具名
  private Integer isValid; // 有効無効。 0:無効、1:有効

  // getter/setterは省略
}