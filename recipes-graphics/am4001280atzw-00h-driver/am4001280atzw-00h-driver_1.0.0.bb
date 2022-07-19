SUMMARY = "AM-4001280ATZQW-00H MIPI-DSI panel driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

SRCREV = "${AUTOREV}"
SRC_URI = " \
    git://github.com/j-gre/panel-ampire-am4001280atzqw00h.git;branch=main;protocol=https \
"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

RPROVIDES_${PN} += "am4001280atzqw00h_driver"

CONFFILES:${PN} = "${sysconfdir}/udev/rules.d/80-display-image.rules"

do_install () {
     if test -s ${WORKDIR}/80-display-image.rules; then
             install -d ${D}/${sysconfdir}/udev/rules.d
             install -m 0644 ${WORKDIR}/80-display-image.rules.conf ${D}/${sysconfdir}/udev/rules.d
     fi
}
 
