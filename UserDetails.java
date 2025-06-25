public class UserDetails {
    public String name;
    public String phone;
    public String altPhone;
    public String pincode;
    public String city;
    public String state;
    public String addressLine1;
    public String addressLine2;
    public String deliveryDate;

    @Override
    public String toString() {
        return String.format(
            "Name: %s\nPhone: %s\nAlt Phone: %s\nAddress: %s, %s, %s, %s\nDelivery Date: %s",
            name, phone, altPhone, addressLine1, addressLine2, city, state, deliveryDate
        );
    }
}