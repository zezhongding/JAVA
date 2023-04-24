    public static void searchFile(File dir, String fileName) {
        if(dir != null && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if(files != null && files.length > 0) {
                for (File file : files) {
                    if(file.isFile()) {
                        if(file.getName().contains(fileName)) {
                            System.out.println("Find " + file.getAbsoluteFile());
                        }
                    } else {
                        searchFile(file, fileName);
                    }
                }
            }
        } else {
//            System.out.println("Sorry, not dir");
        }
