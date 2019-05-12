package org.jeecg.modules.ass.entity;



import lombok.Data;

import java.util.List;

@Data
public class PPage<T> {

    private  List<T> records;

    private long total;

   private long size;

   private long current;

}
