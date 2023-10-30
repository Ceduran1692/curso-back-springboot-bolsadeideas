package com.bolsadeideas.bolsadeideas.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadFileService {
    Resource cargar(String foto) throws MalformedURLException;
    String copiar(MultipartFile archivo) throws IOException;
    boolean eliminar(String foto);
    Path getRuta(String ruta, String foto);
}
