package com.example.hello;
import java.time.LocalDate;

public class Product extends Entity{
	private long RD;
	private long P1UOrder;
	private long HAWB;
	private long MAWB;
	private long STT;
	private int RouteDays;
	private double ProdWgt;
	private String SmartSheet_Priority;
	private String HandedOff;
	private String NewHandOff;
	private String DBSInventory;
	private String Booked;
	private String Arrived; 
	private LocalDate HandOffDate;
	private LocalDate RefGIDate;
	private LocalDate ArrivalDate;
	private LocalDate ETADate;
	private LocalDate RDD;
	private LocalDate TargetDeliveryDate;
	private int daysDifference;
	
	public Product(long RD, long P1UOrder, String country, long HAWB, long MAWB, long STT, int RouteDays,
			double ProdWgt, String SmartSheet_Priority, String HandedOff, String NewHandOff,
			String DBSInventory, String Booked, String Arrived, LocalDate HandOffDate, 
			LocalDate RefGIDate, LocalDate ArrivalDate, LocalDate ETADate, LocalDate RDD, 
			LocalDate TargetDeliveryDate, int daysDifference) {
		super(RD, country);
		this.setRD(RD);
		this.setP1UOrder(P1UOrder);
		this.setHAWB(HAWB);
		this.setMAWB(MAWB);
		this.setSTT(STT);
		this.setRouteDays(RouteDays);
		this.setProdWgt(ProdWgt);
		this.setSmartSheet_Priority(SmartSheet_Priority);
		this.setHandedOff(HandedOff);
		this.setNewHandOff(NewHandOff);
		this.setDBSInventory(DBSInventory);
		this.setBooked(Booked);
		this.setArrived(Arrived);
		this.setHandOffDate(HandOffDate);
		this.setRefGIDate(RefGIDate);
		this.setArrivalDate(ArrivalDate);
		this.setETADate(ETADate);
		this.setRDD(RDD);
		this.setTargetDeliveryDate(TargetDeliveryDate);
		this.setdaysDifference(daysDifference);
		
	}
	public void setdaysDifference(int daysDifference) {
		this.daysDifference = daysDifference;
	}
	public int getDaysDifference() {
		return daysDifference;
	}
	public long getRD() {
		return RD;
	}
	public void setRD(long RD) {
		this.RD = RD;
	}
	public long getP1UOrder() {
		return P1UOrder;
	}
	public void setP1UOrder(long P1UOrder) {
		this.P1UOrder = P1UOrder;
	}
	public long getHAWB() {
		return HAWB;
	}
	public void setHAWB(long HAWB) {
		this.HAWB = HAWB;
	}
	public long getMAWB() {
		return MAWB;
	}
	public void setMAWB(long MAWB) {
		this.MAWB = MAWB;
	}
	public long getSTT() {
		return STT;
	}
	public void setSTT(long STT) {
		this.STT = STT;
	}
	public int getRouteDays() {
		return RouteDays;
	}
	public void setRouteDays(int RouteDays) {
		this.RouteDays = RouteDays;
	}
	public double getProdWgt() {
		return ProdWgt;
	}
	public void setProdWgt(double ProdWgt) {
		this.ProdWgt = ProdWgt;
	}
	public String getSmartSheet_Priority() {
		return SmartSheet_Priority;
	}
	public void setSmartSheet_Priority(String SmartSheet_Priority) {
		this.SmartSheet_Priority = SmartSheet_Priority;
	}
	public String getHandedOff() {
		return HandedOff;
	}
	public void setHandedOff(String HandedOff) {
		this.HandedOff = HandedOff;
	}
	public String getNewHandOff() {
		return NewHandOff;
	}
	public void setNewHandOff(String NewHandOff) {
		this.NewHandOff = NewHandOff;
	}
	public String getDBSInventory() {
		return DBSInventory;
	}
	public void setDBSInventory(String DBSInventory) {
		this.DBSInventory = DBSInventory;
	}
	public String getBooked() {
		return Booked;
	}
	public void setBooked(String Booked) {
		this.Booked = Booked;
	}
	public String getArrived() {
		return Arrived;
	}
	public void setArrived(String Arrived) {
		this.Arrived = Arrived;
	}
	public LocalDate getHandOffDate() {
		return HandOffDate;
	}
	public void setHandOffDate(LocalDate HandOffDate) {
		this.HandOffDate = HandOffDate;
	}
	public LocalDate getRefGIDate() {
		return RefGIDate;
	}
	public void setRefGIDate(LocalDate RefGIDate) {
		this.RefGIDate = RefGIDate;
	}
	public LocalDate getArrivalDate() {
		return ArrivalDate;
	}
	public void setArrivalDate(LocalDate ArrivalDate) {
		this.ArrivalDate = ArrivalDate;
	}
	public LocalDate getETADate() {
		return ETADate;
	}
	public void setETADate(LocalDate ETADate) {
		this.ETADate = ETADate;
	}
	public LocalDate getRDD() {
		return RDD;
	}
	public void setRDD(LocalDate RDD) {
		this.RDD = RDD;
	}
	public LocalDate getTargetDeliveryDate() {
		return TargetDeliveryDate;
	}
	public void setTargetDeliveryDate(LocalDate TargetDeliveryDate) {
		this.TargetDeliveryDate = TargetDeliveryDate;
	}
	
}

