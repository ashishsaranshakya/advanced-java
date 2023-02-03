package Annotaions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Repo<T> {
	public void save(T t) {
//		System.out.println(t);
		var c=t.getClass();
		var canno=c.getAnnotationsByType(Entity.class);
		
		var table=c.getSimpleName().toLowerCase();
		if(canno.length>0 && canno[0].tableName().length()>0) {
			table=canno[0].tableName();
		}
//		System.out.println(table);
		
		ArrayList<String> flist=new ArrayList<>();
		
		for(var f:c.getDeclaredFields()) {
			var anno=f.getAnnotationsByType(Field.class);
			if(anno.length==0) continue;
			var a=anno[0];
			var name=a.columnName();
			var isKey=a.isKey();
			if(name.length()==0) {
				name=f.getName();
			}
			
			if(!isKey) {
				flist.add(name);
			}
			
			
		}
		
		String sqlf=flist.stream().collect(Collectors.joining(","));
		String sqlph=flist.stream().map(s->"?").collect(Collectors.joining(","));			
		System.out.println(sqlf+"\n"+sqlph);
		
		String sql=String.format("insert into %s (%s) values (%s)",table,sqlf,sqlph);
		System.out.println(sql);
//		var z=Arrays
//			.stream(c.getDeclaredFields())
//			.filter(f->f.getAnnotationsByType(Field.class).length>0)
//			.collect(Collectors.toList());
		
//		System.out.println();
//		System.out.println(z);
		
		
		
		
	}
}
