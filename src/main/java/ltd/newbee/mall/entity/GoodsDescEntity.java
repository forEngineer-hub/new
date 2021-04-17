package ltd.newbee.mall.entity;

public class GoodsDescEntity {
	private Long goodsId;
	private String color;
	private String size;
	private String material;
	private String weight;
	private String warrantyYear;
	private String setDate;
	private String warpSize;
	
	
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getWarrantyYear() {
		return warrantyYear;
	}
	public void setWarrantyYear(String warrantyYear) {
		this.warrantyYear = warrantyYear;
	}
	public String getSetDate() {
		return setDate;
	}
	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}
	public String getWarpSize() {
		return warpSize;
	}
	public void setWarpSize(String warpSize) {
		this.warpSize = warpSize;
	}
	
	@Override
	public String toString() {
		return "GoodsDescEntity [goodsId=" + goodsId + ", color=" + color + ", size=" + size + ", material=" + material
				+ ", weight=" + weight + ", warrantyYear=" + warrantyYear + ", setDate=" + setDate + ", warpSize="
				+ warpSize + "]";
	}
}

