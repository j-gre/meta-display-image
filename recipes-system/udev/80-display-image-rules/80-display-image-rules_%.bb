FILESEXTRAPATHS_prepend := "${THISDIR}/80-display-image-rules:"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "${AUTOREV}"

SRC_URI = " \
    file://80-display-image.rules \
    file://di-load-am40001280 \
"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}"

PACKAGES = "${PN}"

// Always re-run those tasks
do_install[nostamp] = "1"
do_unpack[nostamp] = "1"  

do_install () {
    echo "Installing udev rule for am4001280atzw-00h-driver"
    install -m 0666 ${WORKDIR}/80-display-image.rules ${D}/etc/udev/rules.d/80-display-image.rules

    echo "Installing bind script for am4001280atzw-00h-driver"
    install -m 0755 ${WORKDIR}/di-load-am40001280 ${D}/usr/local/bin/di-load-am40001280
}

FILES_${PN} += " /etc/udev/rules.d/80-display-image.rules" 
FILES_${PN} += " /usr/local/bin/di-load-am40001280" 

PROVIDES = "80-display-image-rules"