/**
 * Created by IntelliJ IDEA.
 * User: martin
 * Date: 05/07/11
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */

//Process p = '/home/martin/Downloads/afterPartydata/fastx_toolkit/bin/fastx_clipper -i /home/martin/Downloads/afterPartydata/ERR016332.fastq -Q 33'.execute()



//File input = new File("/home/martin/Downloads/afterPartydata/small.fastq")
File input = new File("/home/martin/Downloads/afterPartydata/ERR016332.fastq")
byte[] inputData = input.readBytes()
println "read ${inputData.length} bytes"
File procInput = new File("/tmp/input.fastq")
procInput.delete()
procInput.append(inputData)

File adapters = new File("/home/martin/Downloads/afterPartydata/adapters.fasta")
byte[] adaptersData = adapters.readBytes()
println "read ${adaptersData.length} bytes"
File procAdapters = new File("/tmp/adapters.fasta")
procAdapters.delete()
procAdapters.append(adaptersData)

String command = "fastq-mcf ${procAdapters.absolutePath} ${procInput.absolutePath}"
Process p = command.execute()
println command

println "Process: ${p}"

//p.out.write(inputData)

// thread to feed the filter with sequences
//Thread.start {
//    int wroteLines = 0
//    def writer = new PrintWriter(new BufferedOutputStream(p.out))
//    input.eachLine {
//        wroteLines++
//        if (wroteLines % 1000 == 0) {
//            println "wrote : $wroteLines"
//        }
//        writer.println(it)
//    }
//    p.out.write(inputData)
//    writer.close()
//    println "written to process"
//}




int lines = 0
StringBuffer file = new StringBuffer()
p.in.newReader().eachLine { line ->
    lines++
    if (lines % 100 == 0) {
        println "read : $lines"
    }
    file.append(line + "\n")
    //println line
}

//
//
 byte[] bf = file.toString().getBytes()
 File output = new File("/tmp/out.fastq")
output.delete()
output.append(bf)