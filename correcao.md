# Correção

## Diagrama

ausente

## Implementação

- repositório fora da estrura padrão
  - por exemplo, gradlew não está na raiz
  - intellij não reconhece como um projeto gradle
  - a execução via wrapper (./gradlew run) não funciona
  - tive que atualizar wrapper (tava na versão 8.3) e o build.gradle (linha 39) para funcionar
- App
  - uso de polimorfismo não é indicado em nenhum lugar, com comentário, como exigido pelo documento da lista
  - como os testes estão específicos por tipo de nave concreta, mas não por interfaces...
    - a nave cargueira não executa o controle automático, apesar de ser Autonoma

## Nota

7 ou 8, dependendo de uma conversa em sala hoje (07/07)