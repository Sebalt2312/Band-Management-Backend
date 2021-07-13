package org.acme.rest.client.enums;

import java.util.Locale;

public enum Instrument {

  SINGER("Singer"),
  GUITAR("Guitar"),
  BASEGUITAR("Baseguitar"),
  DRUMS("Drums"),
  KEYBOARD("Keyboard");

  String instrument;

  Instrument(String instrument) {
    this.instrument=instrument;
  }

  public static Instrument getInstrument(String instrument) {
    instrument = instrument.toUpperCase(Locale.ROOT);
    return Instrument.valueOf(instrument);
  }
}
