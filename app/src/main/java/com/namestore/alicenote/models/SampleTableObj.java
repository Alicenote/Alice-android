package com.namestore.alicenote.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * class này để test: tạo bảng trong database với activeAndroid.
 */
@Table(name = "Sample")
public class SampleTableObj extends Model {

    @Column(name = "name")
    public String name;
}
