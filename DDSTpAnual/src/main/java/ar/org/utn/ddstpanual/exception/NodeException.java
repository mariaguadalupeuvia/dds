package ar.org.utn.ddstpanual.exception;

import org.apache.commons.lang3.StringUtils;

public class NodeException extends Exception {
  private String code;
  private static final long serialVersionUID = -5257620166747093766L;

  public NodeException(final String mensaje) {
    super(mensaje);
  }

  public NodeException(final String mensaje, final String code) {
    super(mensaje);
    this.code = code;
  }

  public NodeException(final String mensaje, final Exception ex) {
    super(mensaje, ex);
  }

  public NodeException(final String mensaje, final String code, final Exception ex) {
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
