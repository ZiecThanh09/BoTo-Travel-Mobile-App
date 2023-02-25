package com.example.bototravel;

public class data {

        public String Diemden;
    public String Diemdi;

        public String Gia;

    public String Ngaydi;
    public String Ngayve;

        public String getGia() {
            return Gia;
        }

        public void setGia(String gia) {
            this.Gia = gia;
        }


        public String getDiemdi() {
            return Diemdi;
        }

        public void setDiemdi(String diemdi) {
            this.Diemdi = diemdi;
        }

        public String getDiemden() {
            return Diemden;
        }

        public void setDiemden(String diemden) {
            this.Diemden = diemden;
        }

        public String getNgaydi() {
            return Ngaydi;
        }

        public void setNgaydi(String ngaydi) {
            this.Ngaydi = ngaydi;
        }

        public String getNgayve() {
            return Ngayve;
        }

        public void setNgayve(String ngayve) {
            this.Ngayve = ngayve;
        }
        public data(){}
    public data(String diemdi){
            this.Diemdi=diemdi;
    }
        public data(String Gia, String Diemdi , String Diemden , String Ngaydi, String Ngayve ){
            this.Gia= Gia;
            this.Diemdi = Diemdi;
            this.Diemden = Diemden;
            this.Ngaydi = Ngaydi;
            this.Ngayve = Ngayve;
        }


}
