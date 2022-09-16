# This bitbake file patches a simple systemd service into the target system to load an in-tree built display driver.

# Therefore, use the support class provided by bitbake to install the unit file to ${D}${systemd_unitdir}/system
inherit systemd
# Setup the package destination for the systemd unit files if systemd is in DISTRO_FEATURES (otherwise leave it empty)
SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${PN}','',d)}"
# Set the systemd service name for the package if systemd is in DISTRO_FEATURES (otherwise leave it empty)
SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES','systemd','di-load-driver.service','',d)}"

# Directory for all included files
FILESEXTRAPATHS_prepend := "${THISDIR}/di-load-driver-0.1:"

SUMMARY="Install a systemd service to load the 
display-image-firmware/drivers/display/panel-ampire-am4001280atzqw00h/panel-ampire-am4001280atzqw00h.c display driver."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "${AUTOREV}"

# Locations of the files to be included (system unit file + bash script)
SRC_URI = " \
    file://di-load-driver.service \
    file://di-load-am40001280.sh \
"

# Auto-increment version number
PV = "1.0+git${SRCPV}"

# Specify directory to copy all the included files to (build directory).
S = "${WORKDIR}"

PACKAGES = "${PN}"

# Always re-run those tasks on a bitbake build
do_install[nostamp] = "1"
do_unpack[nostamp] = "1"  

# Specify installation location of the files on the target system (where D is the destination directory on the target system)
do_install () {
    echo "Trying to install systemd service for installing the am4001280atzw-00h-driver
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		
        # Create "/usr/local/bin" directory on the target if not present and install the bash script
        install -d ${D}/usr/local/bin
        install ${WORKDIR}/di-load-am40001280.sh ${D}/usr/local/bin/di-load-am40001280.sh
        
        # Create "/etc/systemd/system" directory on the target if not present and install the systemd unit file
        install -d ${D}/${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/di-load-driver.service ${D}/${systemd_unitdir}/system

        if [ test -e /file.txt && test -e /file2.txt ]; then
            echo "Installation failed, files not found"
        else
            echo "Installation compleate"
        fi
    else
        echo "Installation failed due to systemd not being present in DISTRO_FEATURES!"
    fi

    ###### Tried this before
    # echo "Installing systemd service for am4001280atzw-00h-driver"
    # install -m 0666 ${WORKDIR}/di-load-driver.service ${D}/etc/systemd/system/di-load-driver.service

    # echo "Installing bind script for am4001280atzw-00h-driver"
    # install -m 0755 ${WORKDIR}/di-load-am40001280.sh ${D}/usr/local/bin/di-load-am40001280.sh
}

# Give yocto a name for a package which bundles all the included files during the final build process.
# In this case, the name is dependent on PN (which is the recipe name).
FILES_${PN} += " /etc/systemd/system/di-load-driver.service" 
FILES_${PN} += " /usr/local/bin/di-load-am40001280.sh" 

# The alias by which to call this recipe
# If other recipes require "di-load-driver", this one will be added to the list of dependencies.
# In our case, the modified machine configuraiton RDEPENDS on this
PROVIDES = "di-load-driver"