package org.jeecg.modules.ws.util;



import lombok.Data;

import java.util.List;

@Data
public class PPage<T> {

    private  List<T> records;

    private long total;

   private long size;

   private long current;

}
