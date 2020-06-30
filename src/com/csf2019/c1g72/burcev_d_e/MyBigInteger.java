package com.csf2019.c1g72.burcev_d_e;

import java.util.ArrayList;
import java.util.List;

public class MyBigInteger {

    public int[] array = new int[1];
    private String value = "0";

    public MyBigInteger(String value) {

        this.value = value;
        array = new int[value.length()];
        for (int i = 0; i < value.length(); i++) {

            array[i] = Character.getNumericValue(value.charAt(value.length() - i - 1));

        }

    }

    private void copyWithoutNulls(int[] result) {
        int var = 0;
        for (int i = result.length - 1; i > 0; i--) {
            if (result[i] == 0) {
                var++;
            } else {
                break;
            }
        }
        this.array = new int[result.length - var];
        for (int i = 0; i < array.length; i++) {
            array[i] = result[i];
        }
        this.changeValue();

    }

    private void changeValue() {
        value = "";
        if (array[array.length - 1] == -1) {
            value += '-';
        } else {
            value += array[array.length - 1];
        }
        for (int i = array.length - 2; i >= 0; i--) {
            value += array[i];
        }

    }

    private boolean isNegative() {
        if (array[array.length - 1] == -1) {
            return true;
        } else {
            return false;
        }

    }

    private void toNegative() {
        if (array[array.length - 1] != -1) {
            value = '-' + value;
            array = new int[value.length()];
            for (int i = 0; i < value.length(); i++) {

                array[i] = Character.getNumericValue(value.charAt(value.length() - i - 1));

            }
        } else {
            this.toPositive();
        }
        this.changeValue();
    }

    private void toPositive() {

        this.array[array.length - 1] = 0;
        this.copyWithoutNulls(array);
        this.changeValue();

    }

    private MyBigInteger addNulls(int nullsCnt){

        MyBigInteger outInt = new MyBigInteger(" ");
        int[] result = new int[this.getSize()+nullsCnt];

        for(int i = nullsCnt; i< result.length; i++){

            result[i] = this.array[i-nullsCnt];

        }
        outInt.copyWithoutNulls(result);
        return outInt;

    }
    private MyBigInteger multiplyByNum(int num){
        if(isNegative()){
           toPositive();
        }
        MyBigInteger outPut = new MyBigInteger("");
        int par = 0;
        int[] result = new int[getSize()+1];

        for(int i = 0;i < getSize();i++){

            if((array[i]*num+par)/10>0){
                result[i] = (array[i]*num+par)%10;
                par = (array[i]*num+par)/10;
            }
            else{

                result[i] = (array[i]*num + par);
                par = 0;

            }
        }
        result[getSize()] = par;
        outPut.copyWithoutNulls(result);
        return  outPut;
    }

    public boolean compare(MyBigInteger other){
        if(other.getSize()> this.getSize()){
            return false;
        }
        else if(this.getSize() == other.getSize()){
            for(int i = this.getSize()-1; i> -1; i--) {
                if (this.array[i] > other.array[i]) {
                    return true;
                } else if (this.array[i] < other.array[i]) {
                    return false;
                }
            }
            return true;
        }
        else{
            return true;
        }
}


    public int getSize() {

        return array.length;

    }

    public String getValue() {

        return value;

    }

    private static int del(int p1, int p2,MyBigInteger other){
        String num = "";
        for(int i = p1; i>p2;i--){
            num += p1;
        }
        MyBigInteger del = new MyBigInteger(num);
        for(int i = 0; i < 10; i++){
            if(other.compare(del)){
                if(i != 0){
                    return i-1;
                }else{
                    return 0;
                }
            }
        }
        return -1;
    }
    //....................................................................... Сложение ..............................................................................
    public void add(MyBigInteger other) {

        int par = 0;
        int[] result = null;
        // ЕСЛИ ОБА ПОЛОЖИТЕЛЬНЫЕ:
        if (!this.isNegative() && !other.isNegative()) {

            if (this.getSize() < other.getSize()) {

                result = new int[other.getSize() + 1];

                for (int i = 0; i < this.getSize(); i++) {

                    if (other.array[i] + this.array[i] + par < 10) {

                        result[i] = other.array[i] + this.array[i] + par;
                        par = 0;

                    }
                    else {

                        result[i] = other.array[i] + this.array[i] + par - 10;
                        par = 1;

                    }
                }

                result[this.getSize()] = other.array[this.getSize()] + par;

                for (int i = this.getSize()+1; i < other.getSize(); i++) {

                    result[i] = other.array[i];

                }

                this.copyWithoutNulls(result);

            }
            else if (this.getSize() > other.getSize()) {

                result = new int[this.getSize() + 1];

                for (int i = 0; i < other.getSize(); i++) {

                    if (other.array[i] + this.array[i] + par < 10) {

                        result[i] = other.array[i] + this.array[i] + par;
                        par = 0;

                    }
                    else {

                        result[i] = other.array[i] + this.array[i] + par - 10;
                        par = 1;

                    }
                }

                result[other.getSize()] = this.array[other.getSize()] + par;

                for (int i = other.getSize() + 1; i < this.getSize(); i++) {

                    result[i] = this.array[i];

                }

                this.copyWithoutNulls(result);

            }
            else {

                result = new int[this.getSize() + 1];

                for (int i = 0; i < other.getSize(); i++) {

                    if (other.array[i] + this.array[i] + par < 10) {

                        result[i] = other.array[i] + this.array[i] + par;
                        par = 0;

                    }
                    else {

                        result[i] = other.array[i] + this.array[i] + par - 10;
                        par = 1;

                    }
                }

                result[other.getSize()] += par;
                this.copyWithoutNulls(result);

            }

        }
        // ...............................................Если один из отриц.............................................................................................................
        else  if(this.isNegative() &&!other.isNegative()){

            this.toPositive();

            this.subtract(other);

            this.toNegative();

        }
        else if (!this.isNegative() && other.isNegative()) {

            other.toPositive();
            this.subtract(other);

            // ............................................................................................Если оба отриц...................................................................
        }
        //................................................Eсли оба отриц.................................................................................................................
        else {

            this.toPositive();
            other.toPositive();

            if (this.getSize() < other.getSize()) {

                result = new int[other.getSize() + 1];

                for (int i = 0; i < this.getSize(); i++) {

                    if (other.array[i] + this.array[i] + par < 10) {

                        result[i] = other.array[i] + this.array[i] + par;
                        par = 0;

                    }
                    else {

                        result[i] = other.array[i] + this.array[i] + par - 10;
                        par = 1;

                    }
                }

                result[this.getSize()] = other.array[this.getSize()] + par;

                for (int i = this.getSize(); i < other.getSize(); i++) {

                    result[i] = other.array[i];

                }

            } else if (this.getSize() > other.getSize()) {

                result = new int[this.getSize() + 1];

                for (int i = 0; i < other.getSize(); i++) {

                    if (other.array[i] + this.array[i] + par < 10) {

                        result[i] = other.array[i] + this.array[i] + par;
                        par = 0;

                    }
                    else {

                        result[i] = other.array[i] + this.array[i] + par - 10;
                        par = 1;

                    }
                }

                result[other.getSize()] = this.array[other.getSize()] + par;

                for (int i = other.getSize() + 1; i < this.getSize(); i++) {

                    result[i] = this.array[i];

                }

            } else {

                result = new int[this.getSize() + 1];

                for (int i = 0; i < other.getSize(); i++) {

                    if (other.array[i] + this.array[i] + par < 10) {

                        result[i] = other.array[i] + this.array[i] + par;
                        par = 0;

                    }
                    else {

                        result[i] = other.array[i] + this.array[i] + par - 10;
                        par = 1;

                    }
                }

                result[other.getSize()] += par;

            }

            this.copyWithoutNulls(result);this.toNegative();

        }
    }
    // ...................................................................... Вычитание  ............................................................................
    public void subtract(MyBigInteger other) {

        int[] result = null;

        if (!this.isNegative() && !other.isNegative()) {

            if (compare(other)) {

                result = new int[this.getSize()];
                int par = 0;

                for (int i = 0; i < other.getSize(); i++) {

                    if (this.array[i] - other.array[i] - par >= 0) {
                        result[i] = this.array[i] - other.array[i] - par;
                        par = 0;

                    }
                    else {

                        result[i] = (this.array[i] - other.array[i] - par + 10);
                        par = 1;

                    }
                }
                if(other.getSize() == this.getSize()){
                    this.copyWithoutNulls(result);
                    return;
                }
                boolean ch = false;
                for (int i = other.getSize(); i < this.getSize(); i++) {
                    if (this.array[i] - par >= 0) {
                        result[i] = this.array[i]- par;
                        par = 0;
                        ch = true;
                    }
                    else if(!ch){
                        result[i] = this.array[i]- par +10;
                    }

                }

                this.copyWithoutNulls(result);

            }
            else if (!compare(other)) {

                result = new int[other.getSize()];
                int par = 0;

                for (int i = 0; i < this.getSize(); i++) {

                    if (other.array[i] - this.array[i] - par >= 0) {
                        result[i] = other.array[i] - this.array[i] - par;
                        par = 0;

                    }
                    else {

                        result[i] = (other.array[i] - this.array[i] - par + 10);
                        par = 1;

                    }
                }
                if(other.getSize() == this.getSize()){
                    this.copyWithoutNulls(result);
                    this.toNegative();
                    return;
                }



                boolean ch = false;
                for (int i = this.getSize() ; i < other.getSize(); i++) {
                    if (other.array[i] - par >= 0) {
                        result[i] = other.array[i] - par;
                        par = 0;
                        ch = true;

                    }
                    else if(!ch){
                        result[i] = other.array[i]- par + 10;
                    }
                }

                this.copyWithoutNulls(result);
                this.toNegative();

            }

        }
        else if(this.isNegative()&&!other.isNegative()){

            this.toPositive();
            this.add(other);
            this.toNegative();

        }
        else{
            other.toPositive();
            this.add(other);

        }
    }
    //....................................................................... Умножение .............................................................................
    public void multiply(MyBigInteger other) {
        if((this.isNegative()&&!other.isNegative())||(!this.isNegative()&&other.isNegative())) {
            List<MyBigInteger> list = new ArrayList<MyBigInteger>();
            for(int i=0;i<other.getSize();i++){
                list.add(this.multiplyByNum(other.array[i]).addNulls(i));
            }
            MyBigInteger result = new MyBigInteger("0");
            for(int i = 0; i<list.size();i++){
                result.add(list.get(i));
            }

            this.copyWithoutNulls(result.array);
            this.toNegative();
        }
        else{
            List<MyBigInteger> list = new ArrayList<MyBigInteger>();
            for(int i=0;i<other.getSize();i++){
                list.add(this.multiplyByNum(other.array[i]).addNulls(i));
            }
            MyBigInteger result = new MyBigInteger("0");
            for(int i = 0; i<list.size();i++){
                result.add(list.get(i));
            }

            this.copyWithoutNulls(result.array);
        }
    }
    //....................................................................... Деление (без остатка)..................................................................

    public void div(MyBigInteger other) {
        if((this.isNegative()&&!other.isNegative())||(!this.isNegative()&&other.isNegative())){
            if(this.compare(other)){

            }
            else{
                    int[] result = new int[1];
                    result[0] = 0;
                    this.copyWithoutNulls(result);
                    return;
            }
        }
        else{

        }
    }

    public void mod(MyBigInteger other){

    }
}
