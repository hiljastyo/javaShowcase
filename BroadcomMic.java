public class BroadcomMic extends BaseMic{

    @Override
    public boolean micOn() {
        if (volume > 100){
            isOn = false;
            return false;
        }
        return true;
    }
}
