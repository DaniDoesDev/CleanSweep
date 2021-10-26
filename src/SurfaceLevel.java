public class SurfaceLevel {
    int SurfaceLevel;

    public SurfaceLevel(int SurfaceLevel) {
        this.SurfaceLevel = SurfaceLevel;
    }

    public enum FloorType {

        BARE, LOW, HIGH, BLOCKED

    }

    public static FloorType getSurfaceLevel(int SurfaceLevel) {
        if (SurfaceLevel == 1)
            return FloorType.BARE;
        if (SurfaceLevel == 2)
            return FloorType.LOW;
        if (SurfaceLevel == 3)
            return FloorType.HIGH;
        else
            return FloorType.BLOCKED;
    }
}