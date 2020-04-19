//package nl.psdcompany.duonavigationdrawer.example.country;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.text.TextUtils;
//
//public class APIModelNotUsed implements Parcelable {
//
//
//    private String location;
//
//    private String countryCode;
//
//    private Double latitude;
//
//    private Double longitude;
//
//    private Integer confirmed;
//
//    private Integer dead;
//
//    private Integer recovered;
//
//    private String updated;
//
//    private String countryFlag;
//
//
//
//    public static final Creator<APIModelNotUsed> CREATOR = new Creator<APIModelNotUsed>() {
//        @Override
//        public APIModelNotUsed createFromParcel(Parcel in) {
//            return new APIModelNotUsed(in);
//        }
//
//        @Override
//        public APIModelNotUsed[] newArray(int size) {
//            return new APIModelNotUsed[size];
//        }
//    };
//
//    public String getCountryFlag() {
//        return countryFlag;
//    }
//
//    public void setCountryFlag(String countryFlag) {
//        this.countryFlag = countryFlag;
//    }
//
//    /**
//     * No args constructor for use in serialization
//     */
//    public APIModelNotUsed() {
//    }
//
//    /**
//     * @param recovered
//     * @param countryCode
//     * @param latitude
//     * @param location
//     * @param dead
//     * @param confirmedt
//     * @param updated
//     * @param longitude
//     */
//    public APIModelNotUsed(String location, String countryCode, Double latitude, Double longitude, Integer confirmed, Integer dead, Integer recovered, String updated) {
//        super();
//        this.location = location;
//        this.countryCode = countryCode;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.confirmed = confirmed;
//        this.dead = dead;
//        this.recovered = recovered;
//        this.updated = updated;
//        if (!TextUtils.isEmpty(countryCode)) {
//            this.countryFlag = "https://www.countryflags.io/" + countryCode + "/flat/64.png";
//        }
//
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getCountryCode() {
//        return countryCode;
//    }
//
//    public void setCountryCode(String countryCode) {
//        this.countryCode = countryCode;
//    }
//
//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }
//
//    public Integer getConfirmed() {
//        return confirmed;
//    }
//
//    public void setConfirmed(Integer confirmed) {
//        this.confirmed = confirmed;
//    }
//
//    public Integer getDead() {
//        return dead;
//    }
//
//    public void setDead(Integer dead) {
//        this.dead = dead;
//    }
//
//    public Integer getRecovered() {
//        return recovered;
//    }
//
//    public void setRecovered(Integer recovered) {
//        this.recovered = recovered;
//    }
//
//    public String getUpdated() {
//        return updated;
//    }
//
//    public void setUpdated(String updated) {
//        this.updated = updated;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//
//        dest.writeString(this.location);
//        dest.writeString(this.countryCode);
//        dest.writeDouble(this.latitude);
//        dest.writeDouble(this.longitude);
//        dest.writeInt(this.confirmed);
//        dest.writeInt(this.dead);
//        dest.writeInt(this.recovered);
//        dest.writeString(this.updated);
//    }
//
//    protected APIModelNotUsed(Parcel in) {
//        location = in.readString();
//        countryCode = in.readString();
//        if (in.readByte() == 0) {
//            latitude = null;
//        } else {
//            latitude = in.readDouble();
//        }
//        if (in.readByte() == 0) {
//            longitude = null;
//        } else {
//            longitude = in.readDouble();
//        }
//        if (in.readByte() == 0) {
//            confirmed = null;
//        } else {
//            confirmed = in.readInt();
//        }
//        if (in.readByte() == 0) {
//            dead = null;
//        } else {
//            dead = in.readInt();
//        }
//        if (in.readByte() == 0) {
//            recovered = null;
//        } else {
//            recovered = in.readInt();
//        }
//        updated = in.readString();
//        countryFlag = in.readString();
//    }
//}