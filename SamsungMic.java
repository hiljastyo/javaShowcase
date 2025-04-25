public class SamsungMic extends BaseMic{

    @Override
    public boolean micOn() {
        if(volume >= 60){
            isOn = false;
            return false;
        }
        return true;
    }
}
