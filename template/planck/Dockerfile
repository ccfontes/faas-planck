FROM ghcr.io/openfaas/of-watchdog:0.9.11 AS watchdog

#FROM ubuntu:20.04
#RUN apt-get update && \
#    apt-get install -y software-properties-common && \
#    add-apt-repository ppa:mfikes/planck && \
#    apt-get update && \
#    apt-get install --yes planck=planck_2.25.0-1ppa1~focal1 && \
#    apt-get uninstall --yes software-properties-common && \
#    apt-get clean && \
#    rm -rf /var/lib/apt/lists/* && \
#    set -e

FROM debian:9.0
RUN apt-get update && \
    apt-get install --yes --no-install-recommends curl=7.52.1-5+deb9u16 libcurl3-gnutls=7.52.1-5+deb9u16 libzip4=1.1.2-1.1+deb9u1 libjavascriptcoregtk-4.0-18=2.18.6-1~deb9u1 && \
    curl -o planck --insecure https://planck-repl.org/releases/2.25.0-debian-9.0-x86_64/planck && \
    apt-get remove --yes curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    chmod +x planck && \
    mv planck /usr/bin && \
    set -e

COPY --from=watchdog /fwatchdog /usr/bin/fwatchdog
EXPOSE 8080
HEALTHCHECK --interval=2s CMD [ -e /tmp/.lock ] || exit 1

ENV HOME="/home/app" 

RUN addgroup --system app && adduser --system --ingroup app app

USER app
WORKDIR $HOME

COPY index.cljs .

COPY function fn

CMD ["fwatchdog"]
