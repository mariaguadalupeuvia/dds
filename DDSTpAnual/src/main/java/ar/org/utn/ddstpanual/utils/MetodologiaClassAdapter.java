package ar.org.utn.ddstpanual.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.FiltroCreciente;
import ar.org.utn.ddstpanual.model.metodologia.FiltroDecreciente;
import ar.org.utn.ddstpanual.model.metodologia.FiltroIgual;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMayor;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMayorIgual;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMenor;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMenorIgual;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;

public class MetodologiaClassAdapter implements JsonSerializer<Metodologia>, JsonDeserializer<Metodologia> {

  @Override
  public JsonElement serialize(Metodologia src, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject result = new JsonObject();
    result.add("nombre", new JsonPrimitive(src.getNombre()));
    result.add("condiciones", context.serialize(src.getCondiciones(), src.getCondiciones().getClass()));
    result.add("orden", context.serialize(src.getOrden(), src.getOrden().getClass()));
    return result;
  }


  @Override
  public Metodologia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    Map<String, Filtro> tiposFiltros = new HashMap<>();
    tiposFiltros.put("Estrictamente creciente", new FiltroCreciente());
    tiposFiltros.put("Estrictamente decreciente", new FiltroDecreciente());
    tiposFiltros.put("Igual", new FiltroIgual());
    tiposFiltros.put("Mayor", new FiltroMayor());
    tiposFiltros.put("Mayor o igual", new FiltroMayorIgual());
    tiposFiltros.put("Menor", new FiltroMenor());
    tiposFiltros.put("Menor o igual", new FiltroMenorIgual());

    List<Condicion> condiciones = new ArrayList<>();

    JsonObject jsonObject = json.getAsJsonObject();
    String nombre = jsonObject.get("nombre").getAsString();
    JsonArray condicionesJson = jsonObject.getAsJsonArray("condiciones");
    Iterator<JsonElement> iterator = condicionesJson.iterator();
    while (iterator.hasNext()) {
      JsonObject condicionJson = iterator.next().getAsJsonObject();
      JsonElement indicadorJson = condicionJson.get("indicador");
      JsonObject filtroJson = condicionJson.get("filtro").getAsJsonObject();
      Indicador indicador = context.deserialize(indicadorJson, Indicador.class);
      String filtroNombre = filtroJson.get("nombre").getAsString();
      Integer filtroValor = filtroJson.get("valor").getAsInt();
      Filtro filtro = tiposFiltros.get(filtroNombre);
      filtro.setNombre(filtroNombre);
      filtro.setValor(filtroValor);
      Condicion condicion = new Condicion();
      condicion.setFiltro(filtro);
      condicion.setIndicador(indicador);
      condiciones.add(condicion);
    }
    JsonElement jsonOrden = jsonObject.get("orden");
    Orden orden = context.deserialize(jsonOrden, Orden.class);
    Metodologia metodologia = new Metodologia();
    metodologia.setNombre(nombre);
    metodologia.setCondiciones(condiciones);
    metodologia.setOrden(orden);
    return metodologia;

  }


}
