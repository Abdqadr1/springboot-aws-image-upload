import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'

function Dropzone(prop) {
  const onDrop = useCallback(acceptedFiles => {
    // Do something with the files
      console.log(acceptedFiles[0])
      const file = acceptedFiles[0]
      const formData = new FormData();
      formData.append("file", file);
      const url = `http://localhost:8080/api/v1/user-profile/${prop.id}/image/upload`; 
      fetch(url, {
          method: "post",
          body: formData
      })
            .then(res => console.log("File uploaded successfully"))
            .catch(err => console.log(err));
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()} className='dropzone'>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the image here ...</p> :
          <p>Drag 'n' drop profile image, or click to select image</p>
      }
    </div>
  )
}

export default Dropzone;