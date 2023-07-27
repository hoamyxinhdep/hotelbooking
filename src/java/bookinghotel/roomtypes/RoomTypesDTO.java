/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookinghotel.roomtypes;

/**
 *
 * @author Admin
 */
public class RoomTypesDTO {
    private int typeId;
    private String typeName;
    private String discription;
    private String image;
    private int maxPeople;
    private String utilities;
    private String bed;
    private float price;

    public float getPrice() {
        return price;
    }

    public RoomTypesDTO(int typeId, String typeName, String discription, String image, int maxPeople, String utilities, String bed, float price) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.discription = discription;
        this.image = image;
        this.maxPeople = maxPeople;
        this.utilities = utilities;
        this.bed = bed;
        this.price = price;
    }
    

    public void setPrice(float price) {
        this.price = price;
    }

    public RoomTypesDTO() {
    }
   
    public RoomTypesDTO(String typeName, String discription, String image, int maxPeople, String utilities, String bed) {
        this.typeName = typeName;
        this.discription = discription;
        this.image = image;
        this.maxPeople = maxPeople;
        this.utilities = utilities;
        this.bed = bed;
    }

    public RoomTypesDTO(int typeId, String typeName, String discription, String image, int maxPeople, String utilities, String bed) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.discription = discription;
        this.image = image;
        this.maxPeople = maxPeople;
        this.utilities = utilities;
        this.bed = bed;
    }
    

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }
    
            
    
}
