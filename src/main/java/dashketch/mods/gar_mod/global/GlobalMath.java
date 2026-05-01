package dashketch.mods.gar_mod.global;

public class GlobalMath {
    public static int modTo(int input, int modTo) {
        if (input > modTo) {
            return (input - (input - modTo));
        } else if (input < modTo) {
            return (input + (modTo - input));
        } else {
            return (input);
        }
    }
}
