package com.org.ait.calc.fragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.org.ait.calc.R;
import com.org.ait.calc.base.BaseFragment;
import com.org.ait.calc.constats.ValueConst;
import com.org.ait.calc.util.Formatter;
import com.org.ait.calc.util.Logger;
import com.org.ait.calc.util.Parser;
import com.org.ait.calc.util.StringMatcher;

import expression.Expression;

/**
 * Created by Shiva for basic calculation on 26-10-2017.
 */

public class BasicCalculator extends BaseFragment implements View.OnClickListener {

    private EditText editText;
    private TextView txtResult, lblCGST, lblSGST, lblGST, lblTotal, valCGST, valSGST, valGST, valTotal;
    private LinearLayout reportLayout;
    private String divide, minus, multiply;
    private String symbolColor, defColor;

    public BasicCalculator() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        divide = getString(R.string.divide_sign);
        minus = getString(R.string.minus_sign);
        multiply = "x";
        symbolColor = getStringColor(getResources().getColor(R.color.btn_side_color));
        defColor = getStringColor(getResources().getColor(R.color.txt_color));

        editText = view.findViewById(R.id.editText);
        disableSoftInputFromAppearing(editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                editText.removeTextChangedListener(this);
                char[] split = s.toString().toCharArray();
                StringBuilder res = new StringBuilder("");
                for (char c : split) {
                    String color = defColor;
                    String ch = c + "";
                    if (ch.equals(ValueConst.PLUS) || ch.equals(ValueConst.PERCENT) || ch.equals(divide) || ch.equals(multiply) || ch.equals(minus)) {
                        color = symbolColor;
                    }
                    // etc...
                    if (color != null) {
                        res.append("<font color=\"" + color + "\">" + c
                                + "</font>");
                    } else {
                        res.append(c);
                    }
                }
                editText.setText(Html.fromHtml(new String(res)));
                editText.addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtResult = view.findViewById(R.id.txtResult);

        lblCGST = view.findViewById(R.id.lblCGST);
        valCGST = view.findViewById(R.id.valCGST);
        lblSGST = view.findViewById(R.id.lblSGST);
        valSGST = view.findViewById(R.id.valSGST);
        lblGST = view.findViewById(R.id.lblGST);
        valGST = view.findViewById(R.id.valGST);
        lblTotal = view.findViewById(R.id.lblTotal);
        valTotal = view.findViewById(R.id.valTotal);
        reportLayout = view.findViewById(R.id.reportLayout);
        setRerportVisibility(View.GONE);
        setClickEvent(view);

        return view;
    }

    private String getStringColor(int color) {
        return String.format("#%06X", 0xFFFFFF & color);
    }

    private void setClickEvent(View topView) {
        try {
            ViewGroup viewGroup = (ViewGroup) topView;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View parrentView = viewGroup.getChildAt(i);
                if (parrentView instanceof CardView) {
                    parrentView.setOnClickListener(this);
                } else if (parrentView instanceof ViewGroup) {
                    setClickEvent(parrentView);
                }
            }
        } catch (Exception e) {
            Logger.onErr(e);
        }
    }

    private void disableSoftInputFromAppearing(EditText editText) {
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plus5:
                calculate(ValueConst.PER_5, ValueConst.GST_5, true, true);
                break;
            case R.id.plus12:
                calculate(ValueConst.PER_12, ValueConst.GST_12, true, true);
                break;
            case R.id.plus18:
                calculate(ValueConst.PER_18, ValueConst.GST_18, true, true);
                break;
            case R.id.plus28:
                calculate(ValueConst.PER_28, ValueConst.GST_28, true, true);
                break;
            case R.id.minus5:
                calculate(ValueConst.PER_5, ValueConst.GST_5, true, false);
                break;
            case R.id.minus12:
                calculate(ValueConst.PER_12, ValueConst.GST_12, true, false);
                break;
            case R.id.minus18:
                calculate(ValueConst.PER_18, ValueConst.GST_18, true, false);
                break;
            case R.id.minus28:
                calculate(ValueConst.PER_28, ValueConst.GST_28, true, false);
                break;
            case R.id.bracket:
                calculate(ValueConst.BRACKET);
                break;
            case R.id.plusMinusBracket:
                calculate(ValueConst.MINUS_BRACKET);
                break;
            case R.id.modOff:
                calculate(ValueConst.PERCENT);
                break;
            case R.id.divide:
                calculate(ValueConst.DIVIDE);
                break;
            case R.id.multiply:
                calculate(ValueConst.MULTIPLY);
                break;
            case R.id.minus:
                calculate(ValueConst.MINUS);
                break;
            case R.id.plus:
                calculate(ValueConst.PLUS);
                break;
            case R.id.point:
                calculate(ValueConst.POINT);
                break;
            case R.id.zero:
                calculate(ValueConst.ZERO);
                break;
            case R.id.one:
                calculate(ValueConst.ONE);
                break;
            case R.id.two:
                calculate(ValueConst.TWO);
                break;
            case R.id.three:
                calculate(ValueConst.THREE);
                break;
            case R.id.four:
                calculate(ValueConst.FOUR);
                break;
            case R.id.five:
                calculate(ValueConst.FIVE);
                break;
            case R.id.six:
                calculate(ValueConst.SIX);
                break;
            case R.id.seven:
                calculate(ValueConst.SEVEN);
                break;
            case R.id.eight:
                calculate(ValueConst.EIGHT);
                break;
            case R.id.nine:
                calculate(ValueConst.NINE);
                break;
            case R.id.clear:
                clear();
                break;
            case R.id.equal:
                equal();
                break;
            case R.id.del:
                del();
                break;
        }
    }

    private void calculate(String value) {
        calculate(value, 0, false, false);
    }

    private void calculate(String value, int val, boolean isGST, boolean isPositive) {
        String finalVal = "";
        if (isGST) {
                finalVal = getText(editText);
                String initialVal = finalVal;
                if (!finalVal.equals("")) {
                    int startPos = editText.getSelectionStart();
                    int endPos = editText.getSelectionEnd();
                    if (startPos != endPos) {
                        finalVal = finalVal.substring(0, startPos) + finalVal.substring(endPos, finalVal.length());
                    }
                    if (StringMatcher.isMatch(finalVal.charAt(finalVal.length() - 1) + "")) {
                        value = value.replace(ValueConst.MULTIPLY, "");
                        finalVal += value;
                        setRerportVisibility(View.GONE);
                        setEditText(finalVal, finalVal.length());
                        evaluateExpression(finalVal);
                    } else {
                        finalVal += value;
                        setEditText(finalVal, finalVal.length());
                        setRerportVisibility(View.VISIBLE);
                        setGstRates(initialVal, val, isPositive);
                    }
                }
        } else {
            setRerportVisibility(View.GONE);
            Object obj[] = getCalcVaue(value);
            finalVal = obj[0].toString();
            setEditText(finalVal, (Integer) obj[1]);
            evaluateExpression(finalVal);
        }
    }

    private void setRerportVisibility(int v) {
        reportLayout.setVisibility(v);
    }

    private void setGstRates(String beforeCalc, int gstRates, boolean isPositive) {
        double calculate = evaluateExpression(beforeCalc);
        double hGst = gstRates / 2.0;
        String totalLbl;
        double total ;
        double gst ;
        if (isPositive) {
            gst = getPercent(calculate, gstRates);
            totalLbl = Formatter.getFormatVal(calculate) + "+" + Formatter.getFormatVal(gst);
        } else {
            gst = -getRemoveGstPer(calculate, gstRates);
            totalLbl = Formatter.getFormatVal(calculate)+" " + " "+ Formatter.getFormatVal(gst);
            gstRates = -gstRates;
        }
        total = calculate + gst;
        String halfGst = Formatter.getFormatVal(gst/2);
        lblCGST.setText("CGST [ " + hGst + "% ]");
        valCGST.setText(halfGst + "");
        lblSGST.setText("SGST [ " + hGst + "% ]");
        valSGST.setText(halfGst + "");
        lblGST.setText("GST [ " + gstRates + "% ]");
        valGST.setText(Formatter.getFormatVal(gst) + "");
        lblTotal.setText("Total [ " + totalLbl + " ]");
        valTotal.setText(Formatter.getFormatVal(total) + "");
        txtResult.setText(String.format("%.0f", gst));

    }

    private double getRemoveGstPer(double originalCost, double gstPer) {
        return originalCost - (originalCost * (100 / (100 + gstPer)));
    }

    private double getPercent(double val, float rates) {
        return ((val * rates) / 100);
    }

    private double evaluateExpression(String finalVal) {
        double result = Double.NaN;
        if (finalVal.contains(ValueConst.POINT)) {
            String digit = "";
            String expression = "";
            for (int i = finalVal.length() - 1; i >= 0; i--) {
                char ch = finalVal.charAt(i);
                if (StringMatcher.symbol.contains(ch + "") || i == 0) {
                    int index = digit.length() - 1;
                    if (index >= 0 && (digit.charAt(index) + "").equals(ValueConst.POINT)) {
                        digit = ch + digit;
                        digit = digit + ValueConst.ZERO;
                        expression = digit + expression;
                        digit = "";
                    } else {
                        digit = ch + digit;
                        expression = digit + expression;
                        digit = "";
                    }
                } else {
                    digit = ch + digit;
                }
            }
            expression = digit + expression;
            finalVal = expression;
        }
        int openBracketCount = StringMatcher.getCharCountInString(finalVal, ValueConst.OPEN_BRACKET);
        int closeBracketCount = StringMatcher.getCharCountInString(finalVal, ValueConst.CLOSE_BRACKET);
        if (openBracketCount > closeBracketCount) {
            for (int i = closeBracketCount; i < openBracketCount; i++) {
                finalVal += ValueConst.CLOSE_BRACKET;
            }
        } else if (closeBracketCount > openBracketCount) {
            for (int i = openBracketCount; i < closeBracketCount; i++) {
                finalVal = ValueConst.OPEN_BRACKET + finalVal;
            }
        }

        Expression ex = new Expression(finalVal);
        boolean correctSyn = ex.checkSyntax();
        if (correctSyn) {
            result = ex.calculate();
            if (isNan(result) || (!StringMatcher.isMatch(finalVal) && result == Parser.getDouble(finalVal))) {
                txtResult.setText("");
            } else {
                txtResult.setText(String.format("%.0f", result));
            }
        } else {
            txtResult.setText("");
        }
        return result;
    }

    private boolean isNan(double val) {
        return Double.isNaN(val);
    }

    private void equal() {
        String result = getText(txtResult);
        if (result.equals("")) {
            evaluateExpression(getText(editText));
        } else {
            setEditText(result, result.length());
            txtResult.setText("");
        }
    }

    private void del() {
        try {
            String strTxt = getText(editText);
            if (!strTxt.equals("")) {
                int cp = editText.getSelectionStart();
                int endPos = editText.getSelectionEnd();
                if (cp == endPos) {
                    if (cp != 0) {
                        cp += -1;
                        String beforeCurosr = strTxt.substring(0, cp);
                        String afterCursor = cp == strTxt.length() ? "" : strTxt.substring(cp + 1, strTxt.length());
                        String afterDeleteStr = beforeCurosr + afterCursor;//strTxt.replace(strTxt.charAt(cp) + "", "");
                        setEditText(afterDeleteStr, cp);
                        evaluateExpression(afterDeleteStr);
                    }
                } else {
                    String beforeCurosr = strTxt.substring(0, cp);
                    String afterCursor = strTxt.substring(endPos, strTxt.length());
                    String afterDeleteStr = beforeCurosr + afterCursor;//strTxt.replace(strTxt.charAt(cp) + "", "");
                    setEditText(afterDeleteStr, cp);
                    evaluateExpression(afterDeleteStr);
                }
            }
        } catch (Exception e) {
            Logger.onErr(e);
        }
    }

    private Object[] getCalcVaue(String value) {
        Object obj[] = null;
        try {
            String finalVal = getText(editText);
            int startPos = editText.getSelectionStart();
            int endPos = editText.getSelectionEnd();
            int initialLen = finalVal.length();
            String firstValBeforeCursor = startPos > 0 ? finalVal.substring(startPos - 1, startPos) : "";
            int cp = startPos;
            if (value.equals(ValueConst.PERCENT) && finalVal.isEmpty()) {
                //do nothing
            } else if (value.equals(ValueConst.ZERO) && finalVal.equals(ValueConst.ZERO)) {
                finalVal = value;
            } else if (value.equals(ValueConst.BRACKET)) {
                if (finalVal.equals("")) {
                    finalVal = ValueConst.OPEN_BRACKET;
                    cp = 1;
                } else {
                    String firstValueOfBeforeCursor = cp > 0 ? finalVal.substring(cp - 1, cp) : "";
                    String beforCursorVal = finalVal.substring(0, cp);
                    int openBracketCount = StringMatcher.getCharCountInString(beforCursorVal, ValueConst.OPEN_BRACKET);
                    int closeBracketCount = StringMatcher.getCharCountInString(beforCursorVal, ValueConst.CLOSE_BRACKET);
                    ;
                    int count = openBracketCount - closeBracketCount;
                    /*String brackets = "";
                    for(int i=0;i<count;i++){
                        brackets += ValueConst.CLOSE_BRACKET;
                    }*/

                    String afterCursorVal = finalVal.substring(endPos, finalVal.length());
                    finalVal = firstValueOfBeforeCursor.equals(ValueConst.OPEN_BRACKET) ? beforCursorVal + ValueConst.OPEN_BRACKET + afterCursorVal :
                            StringMatcher.symbol.contains(firstValueOfBeforeCursor) ? beforCursorVal + ValueConst.OPEN_BRACKET + afterCursorVal :
                                    StringMatcher.NUMBERS.contains(firstValueOfBeforeCursor) ? count > 0 ? beforCursorVal + ValueConst.CLOSE_BRACKET + afterCursorVal : beforCursorVal + ValueConst.MULTIPLY + ValueConst.OPEN_BRACKET + afterCursorVal :
                                            firstValueOfBeforeCursor.equals(ValueConst.CLOSE_BRACKET) ? count > 0 ? beforCursorVal + ValueConst.CLOSE_BRACKET + afterCursorVal : beforCursorVal + ValueConst.MULTIPLY + ValueConst.OPEN_BRACKET + afterCursorVal :
                                                    firstValueOfBeforeCursor.equals(ValueConst.POINT) ? count > 0 ? beforCursorVal + ValueConst.ZERO + ValueConst.CLOSE_BRACKET + afterCursorVal :
                                                            beforCursorVal + ValueConst.ZERO + ValueConst.MULTIPLY + ValueConst.OPEN_BRACKET + afterCursorVal : "000";
                    cp = cp + finalVal.length() - initialLen;
                }
            } else if (StringMatcher.NUMBERS.contains(value)) {
                String afterCursorVal = finalVal.substring(endPos, finalVal.length());
                if (cp > 0) {
                    String beforeCusorVal = finalVal.substring(0, cp);
                    if (firstValBeforeCursor.equals(ValueConst.PERCENT) || firstValBeforeCursor.equals(ValueConst.CLOSE_BRACKET)) {
                        value = ValueConst.MULTIPLY + value;

                        //cp += value.length();
                    }
                    finalVal = beforeCusorVal + value + afterCursorVal;
                } else {
                    finalVal = value + afterCursorVal;
                }
                cp += finalVal.length() - initialLen;

            } else if (value.equals(ValueConst.PERCENT) && (StringMatcher.symbol.contains(firstValBeforeCursor) || firstValBeforeCursor.equals(ValueConst.PERCENT))) {
                Toast.makeText(getContext(), "Invalid Format", Toast.LENGTH_SHORT).show();
            } else if (value.equals(ValueConst.MINUS_BRACKET)) {
                String afterCursorVal = finalVal.substring(cp, finalVal.length());
                if (cp != endPos) {
                    afterCursorVal = finalVal.substring(endPos, finalVal.length());
                }
                String beforeCursor = finalVal.substring(0, cp);
                int stopIndex = cp;
                String digit = "";
                for (int i = beforeCursor.length() - 1; i >= 0; i--) {
                    char ch = beforeCursor.charAt(i);
                    if (StringMatcher.symbol.contains(ch + "")) {
                        if (ch == '-' && (beforeCursor.charAt((i > 0 ? i - 1 : i)) + "" + ch).equals(ValueConst.MINUS_BRACKET)) {
                            //do nothing
                        } else {
                            stopIndex = i + 1;
                            break;
                        }
                    }
                    digit = ch + digit;
                }
                String beforeSignVal = finalVal.substring(0, stopIndex);
                beforeSignVal = digit.equals(beforeSignVal) ? "" : beforeSignVal;
                finalVal = digit.equals("") ? beforeSignVal + value + afterCursorVal : beforeSignVal + value + digit + afterCursorVal;
                if (finalVal.contains(ValueConst.MINUS_BRACKET + ValueConst.MINUS_BRACKET)) {
                    finalVal = finalVal.replace(ValueConst.MINUS_BRACKET + ValueConst.MINUS_BRACKET, "");
                    cp -= 2;
                } else {
                    cp += 2;
                }
                // }
                // finalVal = finalVal.substring(0,cp);
            } else if (value.equals(ValueConst.POINT)) {
                //String firstValBeforeCursor =cp>0?finalVal.substring(cp-1, cp):"";
                String beforeCursor = finalVal.substring(0, cp);
                //finalVal = beforeCursor + "#"+value + finalVal.substring(endPos, finalVal.length());
                String digit = "";
                for (int i = beforeCursor.length() - 1; i >= 0; i--) {
                    String ch = beforeCursor.charAt(i) + "";
                    if (StringMatcher.symbol.contains(ch) || ch.equals(ValueConst.CLOSE_BRACKET)) {
                        break;
                    }
                    digit = ch + digit;
                }
                String afterCursorVal = finalVal.substring(endPos, finalVal.length());
                for (int i = cp; i < finalVal.length(); i++) {
                    String ch = afterCursorVal.charAt(i - cp) + "";
                    if (StringMatcher.symbol.contains(ch) || ch.equals(ValueConst.CLOSE_BRACKET)) {
                        break;
                    }
                    digit += ch;
                }
                int len = finalVal.length();
                value = StringMatcher.symbol.contains(firstValBeforeCursor) ? "0" + value :
                        firstValBeforeCursor.equals(ValueConst.CLOSE_BRACKET) ? "*0" + value :
                                firstValBeforeCursor.equals(ValueConst.OPEN_BRACKET) ? "0" + value : value;
                ;
                finalVal = digit.equals("") ? beforeCursor + value + afterCursorVal :
                        digit.contains(ValueConst.POINT) ? finalVal : beforeCursor + value + afterCursorVal;
                cp = finalVal.length() > len ? cp + (finalVal.length() - len) : cp;
            } else {
                Object objCorrectSymbol[] = getCorrectSymbol(finalVal, value);
                if ((Boolean) objCorrectSymbol[0]) {
                    if (startPos == endPos) {
                        String beforeCursor = finalVal.substring(0, startPos);
                        finalVal = beforeCursor + value + finalVal.substring(startPos, finalVal.length());//finalVal.replace(beforeCursor, "");
                    } else {
                        String beforeCursor = finalVal.substring(0, startPos);
                        finalVal = beforeCursor + value + finalVal.substring(endPos, finalVal.length());//finalVal.replace(finalVal.substring(startPos, endPos), value);
                    }
                    cp = startPos + value.length();
                } else {
                    finalVal = objCorrectSymbol[1].toString();
                    cp = value.length() == 1 ? startPos : startPos + value.length() - 1;
                }
            }

            /*if(value.equals(ValueConst.POINT)){
                String changeVal = getDecimalVal(finalVal);
                if(!changeVal.equals(finalVal)){
                    finalVal = changeVal;
                    cp--;
                }
            }*/
            obj = new Object[2];
            obj[0] = finalVal;
            obj[1] = cp;
        } catch (Exception e) {
            Logger.onErr(e);
        }
        return obj;
    }

    private Object[] getCorrectSymbol(String finalVal, String selValue) {
        Object obj[] = new Object[2];
        boolean flag = true;
        int cp = editText.getSelectionStart();
        if (StringMatcher.isMatch(selValue)) {
            if (finalVal.equals("")) {
            /*if(selValue.equals(ValueConst.POINT)){
                finalVal = "0.";
            }*/
                obj[0] = false;
                obj[1] = finalVal;
                return obj;
            }
            String beforeCursorVal = finalVal.charAt(cp - 1) + "";
            String afterCursorVal = cp == finalVal.length() ? "" : finalVal.charAt(cp) + "";
            if (StringMatcher.isMatch(beforeCursorVal)) {
                String firstVal = finalVal.substring(0, cp - 1);
                String lastVal = cp == finalVal.length() ? "" : finalVal.substring(cp, finalVal.length());
                finalVal = firstVal + selValue + lastVal;
                flag = false;
            } else if (StringMatcher.isMatch(afterCursorVal)) {
                String firstVal = finalVal.substring(0, cp);
                String lastVal = cp == finalVal.length() - 1 ? finalVal.substring(cp, finalVal.length() - 1) :
                        finalVal.substring(cp + 1, finalVal.length());
                finalVal = firstVal + selValue + lastVal;
                flag = false;
            }
        }
        obj[0] = flag;
        obj[1] = finalVal;
        return obj;
    }

    private void clear() {
        setEditText("", 0);
        txtResult.setText("");
        setRerportVisibility(View.GONE);
    }

    private String getText(TextView text) {
        String str = text.getText().toString().trim();
        str = str.replace(multiply, ValueConst.MULTIPLY);
        str = str.replace(divide, ValueConst.DIVIDE);
        str = str.replace(minus, ValueConst.MINUS);
        return str;
    }

    private void setEditText(String value, int cursorPos) {
        try {
            value = value.replace(ValueConst.MULTIPLY, multiply);
            value = value.replace(ValueConst.DIVIDE, divide);
            value = value.replace(ValueConst.MINUS, minus);
            editText.setText(value);
            editText.requestFocus();
            editText.setSelection(cursorPos);
        } catch (Exception e) {
            Logger.onErr(e);
        }
    }
}