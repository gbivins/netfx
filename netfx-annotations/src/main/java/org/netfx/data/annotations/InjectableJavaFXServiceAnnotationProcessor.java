/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.data.annotations;

import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import org.openide.filesystems.annotations.LayerBuilder;
import org.openide.filesystems.annotations.LayerGeneratingProcessor;
import org.openide.filesystems.annotations.LayerGenerationException;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author gbivins
 */
@ServiceProvider(service = Processor.class)
@SupportedAnnotationTypes({"org.netfx.data.annotations.InjectableJavaFXService"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class InjectableJavaFXServiceAnnotationProcessor extends LayerGeneratingProcessor {

    @Override
    protected boolean handleProcess(Set<? extends TypeElement> set, RoundEnvironment re) throws LayerGenerationException {
        Elements elements = processingEnv.getElementUtils();
        Messager messager = processingEnv.getMessager();

        messager.printMessage(Diagnostic.Kind.NOTE, "Blay");
        for (Element e : re.getElementsAnnotatedWith(InjectableJavaFXService.class)) {

            TypeElement clazz = (TypeElement) e;
            InjectableJavaFXService injectable = clazz.getAnnotation(InjectableJavaFXService.class);

            LayerBuilder.File f;
            try {
                f = layer(e).file(injectable.injectInto().getName().replace('.', '/') + "/injectables/" + clazz.getQualifiedName().toString().replace('.', '-') + ".instance");
            } 
            catch (MirroredTypeException mte) 
            {
                f = layer(e).file(mte.getTypeMirror().toString().replace('.', '/')+/*injectable.injectInto().getName().replace('.', '/') +*/
                        "/injectables/" +
                        /*mte.getTypeMirror()*/
                        clazz.getQualifiedName().toString().replace('.', '-') + 
                        ".instance");
            }


            if (f != null) {
                f.write();
            }
        }
        return true;
    }
}
