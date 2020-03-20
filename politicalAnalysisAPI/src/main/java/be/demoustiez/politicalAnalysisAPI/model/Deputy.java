package be.demoustiez.politicalAnalysisAPI.model;

public class Deputy {
    private Integer id;
    private String firstName;
    private String lastName;
    private String civilTitle;
    private String group;
    private String circonscription;
    private String province;
    private Address address;
    private String telNumber;
    private String fax;
    private String email;
    private String url;
    private String facebookURL;
    private String twitterURL;
    private BureauInfo bureauPosition;
    private BureauInfo wideBureauPosition;
    private BureauInfo confPosition;
    private boolean isGroupPres;
    private String pictureURL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCivilTitle() {
        return civilTitle;
    }

    public void setCivilTitle(String civilTitle) {
        this.civilTitle = civilTitle;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getCirconscription() {
        return circonscription;
    }

    public void setCirconscription(String circonscription) {
        this.circonscription = circonscription;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFacebookURL() {
        return facebookURL;
    }

    public void setFacebookURL(String facebookURL) {
        this.facebookURL = facebookURL;
    }

    public String getTwitterURL() {
        return twitterURL;
    }

    public void setTwitterURL(String twitterURL) {
        this.twitterURL = twitterURL;
    }


    public boolean isGroupPres() {
        return isGroupPres;
    }

    public void setGroupPres(boolean groupPres) {
        isGroupPres = groupPres;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public BureauInfo getBureauPosition() {
        return bureauPosition;
    }

    public void setBureauPosition(BureauInfo bureauPosition) {
        this.bureauPosition = bureauPosition;
    }

    public BureauInfo getWideBureauPosition() {
        return wideBureauPosition;
    }

    public void setWideBureauPosition(BureauInfo wideBureauPosition) {
        this.wideBureauPosition = wideBureauPosition;
    }

    public BureauInfo getConfPosition() {
        return confPosition;
    }

    public void setConfPosition(BureauInfo confPosition) {
        this.confPosition = confPosition;
    }
    public String getAdressString(){
        return this.province+" > "+this.address.toString();
    }
    public static class Address{
        private String street;
        private String zip;
        private String local;

        public String getStreet() {
            return street;
        }

        public Address setStreet(String street) {
            this.street = street;
            return this;
        }

        public String getZip() {
            return zip;
        }

        public Address setZip(String zip) {
            this.zip = zip;
            return this;
        }

        public String getLocal() {
            return local;
        }

        public Address setLocal(String local) {
            this.local = local;
            return this;
        }
        @Override
        public String toString(){
            return this.local + " ("+this.zip+")";
        }
    }
    public static class BureauInfo{
        private String function;
        private Integer order;

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }
    }
}
