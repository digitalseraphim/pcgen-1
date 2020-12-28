package pcgen.gui2.javafx;

import pcgen.gui2.tools.Icons;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PCGenActionProxy {
	boolean enabled() default true;
	String type() default "";
}