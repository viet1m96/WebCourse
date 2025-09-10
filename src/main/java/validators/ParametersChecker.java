package validators;

import exceptions.InputFault;

public class ParametersChecker {

    private static final String PARAMETER_NOT_FOUND = " is not found!";
    private static final String TOO_LONG = " is too long!";
    private static final String WRONG_FORMAT= " is in wrong format!";
    private static final String NOT_IN_RANGE = " is not in range!";
    private static final String NOT_IN_LIST = " is not in its list";


    public static void checkParams(String x, String y, String R) throws InputFault {
        if(x == null || x.isEmpty()) throw new InputFault("x" + PARAMETER_NOT_FOUND);
        if(y == null || y.isEmpty()) throw new InputFault("y" + PARAMETER_NOT_FOUND);
        if(R == null || R.isEmpty()) throw new InputFault("R" + PARAMETER_NOT_FOUND);
        if(!isShortEnough(x)) throw new InputFault("x" + TOO_LONG);
        if(!isShortEnough(y)) throw new InputFault("y" + TOO_LONG);
        if(!isShortEnough(R)) throw new InputFault("R" + TOO_LONG);
        checkX(x);
        checkY(y);
        checkR(R);
    }

    private static boolean isShortEnough(String num) {
        int pos = -1;
        for(int i = 0; i < num.length(); i++) {
            if(num.charAt(i) == '.') {
                pos = i;
                break;
            }
        }
        if(pos == -1) return true;
        int cnt = 0;
        for(int i = pos + 1; i < num.length(); i++) {
            cnt++;
        }
        return cnt <= 2;
    }

    private static boolean isIntegerOrHalf(double num) {
        double twoY = num * 2;
        return Math.abs(twoY - Math.round(twoY)) < 1e-9;
    }
    private static void checkX(String x) throws InputFault {
        double numX;
        try {
            numX = Double.parseDouble(x);
        } catch(NumberFormatException e) {
            throw new InputFault("x" + WRONG_FORMAT);
        }
        if(numX < -5 || numX > 5) throw new InputFault("x" + NOT_IN_RANGE);
    }

    private static void checkY(String y) throws InputFault {
        double numY;
        try {
            numY = Double.parseDouble(y);
        } catch(NumberFormatException e) {
            throw new InputFault("y" + WRONG_FORMAT);
        }
        if(numY < -2 || numY > 2) throw new InputFault("y" + NOT_IN_RANGE);
        if(!isIntegerOrHalf(numY)) throw new InputFault("y" + NOT_IN_LIST);
    }

    private static void checkR(String R) throws InputFault {
        double numR;
        try {
            numR = Double.parseDouble(R);
        } catch(NumberFormatException e) {
            throw new InputFault("R" + WRONG_FORMAT);
        }
        if(numR < 1 || numR > 3) throw new InputFault("R" + NOT_IN_RANGE);
        if(!isIntegerOrHalf(numR)) throw new InputFault("R" + NOT_IN_LIST);
    }


}
