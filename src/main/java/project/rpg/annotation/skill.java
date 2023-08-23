package project.rpg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
public @interface skill {  //심심해서 추가해봄 마커 어노테이션임
    String name();
}
