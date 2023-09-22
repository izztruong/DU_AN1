package com.example.app_user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_user.DAO.GioHangDAO;
import com.example.app_user.DAO.tbGioHangDAO;
import com.example.app_user.Model.Sach;
import com.example.app_user.Model.tbGioHang;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;

public class ChiTietSachActivity extends AppCompatActivity {
    TextView txtmota,txtgiasach,txttensach;
    ImageView ivctsp;
    Toolbar toolbar;
    Button btnmaungay,btnthemgiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        txtmota=findViewById(R.id.txtmotactsp);
        txtgiasach=findViewById(R.id.txtgiactsp);
        txttensach=findViewById(R.id.txttensachctsp);
        ivctsp=findViewById(R.id.ivctsp);
        toolbar=findViewById(R.id.toolbarctsp);
        btnmaungay=findViewById(R.id.btnmuangay);
        btnthemgiohang=findViewById(R.id.btnthemgiohang);
        ActionToolBar();
        initdata();
        btnthemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickThemGioHang();
            }
        });
        btnmaungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogmuangay();

            }
        });

    }
    private void initdata() {
        Sach sach= (Sach) getIntent().getSerializableExtra("chitietsanpham");
        txttensach.setText(sach.getTenSach());

        txtgiasach.setText("Giá: "+sach.getGia()+"đ");
        txtmota.setText(sach.getMoTa());
        Picasso.get().load(sach.getLinkAnh()).into(ivctsp);

    }

    private void ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void clickThemGioHang(){

        Sach sach= (Sach) getIntent().getSerializableExtra("chitietsanpham");
        int idSach=sach.getIdSach();
        GioHangDAO gioHangDAO=new GioHangDAO();
        try {
            if(gioHangDAO.checkthemgiohang(idSach,MainActivity.idKhachHang)){
                gioHangDAO.ThemVaoGioHang(String.valueOf(idSach),String.valueOf(MainActivity.idKhachHang));
                Toast.makeText(this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();


        }else {
                Toast.makeText(this, "sản phẩm đã có trong giỏ hàng ", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void showdialogmuangay(){
        Sach sach= (Sach) getIntent().getSerializableExtra("chitietsanpham");
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_muangay,null);
        ImageView ivanh,ivcong,ivtru;
        TextView txtgia,txtsoluong;
        Button btnmuangay;
        ivanh=view.findViewById(R.id.ivmuangaydialog);
        ivcong=view.findViewById(R.id.ivcongmaungaydialog);
        ivtru=view.findViewById(R.id.ivtrumuangaydialog);
        txtgia=view.findViewById(R.id.txtgiamaungaydialog);
        txtsoluong=view.findViewById(R.id.txtsoluongmuangaydialog);
        btnmuangay=view.findViewById(R.id.btnmuangaydialog);
        builder.setView(view);
        ivcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong= Integer.parseInt(txtsoluong.getText().toString());
                soluong++;
                txtsoluong.setText(String.valueOf(soluong));
            }
        });
        ivtru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong= Integer.parseInt(txtsoluong.getText().toString());
                soluong--;
                txtsoluong.setText(String.valueOf(soluong));
            }
        });
        Picasso.get().load(sach.getLinkAnh()).into(ivanh);
        txtgia.setText(sach.getGia()+"đ");
        btnmuangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbGioHang gioHang=new tbGioHang();
                gioHang.setIdSach(sach.getIdSach());
                gioHang.setGia(sach.getGia());
                gioHang.setTenSach(sach.getTenSach());
                gioHang.setLinkAnh(sach.getLinkAnh());
                gioHang.setSoLuong(Integer.parseInt(txtsoluong.getText().toString()));

                tbGioHangDAO dao=new tbGioHangDAO(getApplicationContext());
                dao.delete();
                dao.themvaoHoaDon(gioHang);
                Intent intent=new Intent(ChiTietSachActivity.this,HoaDonChiTietActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}