package com.bolsadeideas.bolsadeideas.services.implementations;

import com.bolsadeideas.bolsadeideas.services.interfaces.IUploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class UploadFileService implements IUploadFileService {

    private String RUTA_DEFAULT= "src/main/resources/static/images";
    private String FOTO_DEFAULT= "user.png";
    private String RUTA= "C:\\Users\\Carlos\\Educacion\\Cursos\\Spring-Angular\\Angular_Spring_Gabriel-Guzman\\Seccion_2\\uploads";

    @Override
    public Resource cargar(String foto) throws MalformedURLException {
        Resource recurso= new UrlResource(this.getRuta(RUTA, foto).toUri());

        if(recurso.exists() && !recurso.isReadable()){
            log.error("Error no se pudo cargar la imagen: "+foto);
            recurso= new UrlResource(this.getRuta(RUTA_DEFAULT,FOTO_DEFAULT).toUri());
        }
        return recurso;
    }

    @Override
    public String copiar(MultipartFile archivo) throws IOException {
        String nombreArchivo = UUID.randomUUID()+"_"+archivo.getOriginalFilename().replace(" ","");
        Files.copy(archivo.getInputStream(), this.getRuta(RUTA,nombreArchivo));
        return  nombreArchivo;

    }

    @Override
    public boolean eliminar(String foto) {

        File archivoAnterior= this.getRuta(RUTA,foto).toFile();
        return archivoAnterior.delete();
    }

    @Override
    public Path getRuta(String ruta,String foto) {
        return  Paths.get(ruta).resolve(foto).toAbsolutePath();

    }
}
