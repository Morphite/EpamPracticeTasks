package ua.nure.leonov.practice7.entity;

public class FillChar {

    private String volume;
    private String packageMaterial;

    public FillChar(String volume, String packageMaterial){
        this.volume = volume;
        this.packageMaterial = packageMaterial;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPackageMaterial() {
        return packageMaterial;
    }

    public void setPackageMaterial(String packageMaterial) {
        this.packageMaterial = packageMaterial;
    }

    @Override
    public String toString() {
        return "FillChar{" +
                "volume='" + volume + '\'' +
                ", packageMaterial='" + packageMaterial + '\'' +
                '}';
    }
}
