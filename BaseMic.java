public abstract class BaseMic implements ITelephoneMic {
    protected int volume;
    protected boolean isOn;

    @Override
    public boolean micOff() {
        isOn = false;
        return true;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int getVolume() {
        return volume;
    }


}
