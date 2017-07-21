package ar.org.utn.ddstpanual.exception;

import org.apache.commons.lang3.StringUtils;

public class FiltroException extends Exception {

  private static final long serialVersionUID = -8351405844794064335L;
  private String code;

  public FiltroException(final String mensaje) {
    super(mensaje);
  }

  public FiltroException(final String mensaje, final String code) {
    super(mensaje);
    this.code = code;
  }

  public FiltroException(final String mensaje, final Exception ex) {
    super(mensaje, ex);
  }

  public FiltroException(final String mensaje, final String code, final Exception ex) {
    super(mensaje, ex);
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public boolean hasErrorCode() {
    return !StringUtils.isBlank(getCode());
  }

}
