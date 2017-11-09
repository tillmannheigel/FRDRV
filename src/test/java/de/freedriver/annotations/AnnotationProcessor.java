package de.freedriver.annotations;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnnotationProcessor extends AbstractProcessor{
    @Override public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Hello compile time message");

        Set<? extends Element> elementsAnnotatedWithSkip = roundEnv.getElementsAnnotatedWith(Skip.class);
        for (Element skipElement: elementsAnnotatedWithSkip) {
            System.out.println(skipElement.toString());
        }
        return true;
    }
}
